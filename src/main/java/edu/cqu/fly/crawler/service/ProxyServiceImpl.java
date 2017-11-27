package edu.cqu.fly.crawler.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import edu.cqu.fly.crawler.config.StdConfig;
import edu.cqu.fly.crawler.controller.IPProxyController;
import edu.cqu.fly.crawler.dao.CommonDao;
import edu.cqu.fly.crawler.dao.CommonDaoImpl;
import edu.cqu.fly.crawler.util.SystemUtils;
import edu.cqu.fly.erawler.domain.IPProxy;
import javax.annotation.Resource;

@Service("proxyService")
@Transactional
public class ProxyServiceImpl implements ProxyService {
	String configPath = TaskServiceImpl.class.getClassLoader().getResource("config.xml").getPath();

	StdConfig config = new StdConfig(configPath);
	String proxyUrl = config.getXpathText("config/proxyServer/text()");// 代理地址
	String proxyReg = config.getXpathText("config/proxyReg/text()");// ip与端口的正则表达式
	String encode = config.getXpathText("config/proxyCode/text()");// 页面编码
	int ipInterval = Integer.parseInt(config.getXpathText("config/getProxyIntervalTime/text()"));// 再次重新获取的时间
	long getProxyTime = System.currentTimeMillis();
	@Resource(name = "commonDao")

	CommonDao commonDao;

	public synchronized String getIp() {
		// TODO Auto-generated method stub
		// 1、判断是否还有可用ip，新IP与上次使用间隔较长的ip
		// 如果上次去取得代理IP列表的时间超过30分钟，则删除原有的ip列表，重新获取添加到数据库
		if (this.getProxyTime + ipInterval * 60 * 1000 < System.currentTimeMillis()) {
			// 删除
			//commonDao.executeJPQL("delete from IPProxy");
			// 重新获取并保存
			this.saveIp(this.ipList(this.getOneHtml(), proxyReg));
			this.getProxyTime=System.currentTimeMillis();//重新为获取代理的时间赋值
		}

		// 判断ip表是否为空，如果为空，则需要获取IP
		IPProxy ipProxy = null;
		//IPProxy ipProxy = new IPProxy();
		int errorCount = 0;
		JSONObject json = new JSONObject();
		while (ipProxy == null) {
			if (errorCount == 5) {
				json.put("msg", "代理IP获取失败：连续5次出错！");
				json.put("success", false);

				return json.toString();
			}

			List<IPProxy> ipProxyList = commonDao.readEntityByJPQL(
					"from IPProxy a where using=0 order by a.addTime, a.usedTime ", 0, 1, IPProxy.class);
			if (ipProxyList.size() == 0) {
				this.saveIp(this.ipList(this.getOneHtml(), proxyReg));
			} else {
				ipProxy = ipProxyList.get(0);
				ipProxy.setUsedTime(new Date());
				ipProxy.setTimes(ipProxy.getTimes() + 1);
				ipProxy.setUsing(1);
				commonDao.saveEntity(ipProxy);
				break;
			}
			errorCount++;
		}

		try {
			json.put("ip", ipProxy.getIp());
			json.put("port", ipProxy.getPort());
			//json.put("ip", "http-dyn.abuyun.com");
			//json.put("port", 9020);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", "代理IP获取失败，" + e.toString());
			json.put("success", false);
		}
		return json.toString();

	}

	/**
	 * 获取网页内容by URL
	 * 
	 * @param urlString
	 *            网址
	 * @return 网页内容
	 * @throws IOException
	 */
	public String getOneHtml() {
		StringBuffer content = new StringBuffer();
		HttpURLConnection connection = null;
		try {
			URL u = new URL(proxyUrl);
			connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("GET");
			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream in = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(in, encode);
				BufferedReader reader = new BufferedReader(isr);
				String line = null;
				while ((line = reader.readLine()) != null) {
					content.append(line);
					content.append("\n");
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return content.toString();
	}

	public void saveIp(Map<String, Integer> proxyIpMap) {
		for (String proxyHost : proxyIpMap.keySet()) {
			Integer proxyPort = proxyIpMap.get(proxyHost);

			IPProxy ipProxy = new IPProxy();
			ipProxy.setIp(proxyHost);
			ipProxy.setPort(proxyPort);
			ipProxy.setAddTime(new Date());
			ipProxy.setTimes(0);
			ipProxy.setUsing(0);
			ipProxy.setAvailable(1);
			commonDao.saveEntity(ipProxy);
		}
	}

	/**
	 * 批量代理IP有效检测 ， 可以先直接批量获取，不验证，让客户端自己去验证
	 *
	 * @param proxyIpMap
	 * @param reqUrl
	 */
	public void checkProxyIp(Map<String, Integer> proxyIpMap, String reqUrl) {

		for (String proxyHost : proxyIpMap.keySet()) {
			Integer proxyPort = proxyIpMap.get(proxyHost);

			int statusCode = 0;
			try {
				HttpClient httpClient = new HttpClient();
				httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);

				// 连接超时时间（默认6秒 6000ms） 单位毫秒（ms）
				int connectionTimeout = 6000;
				// 读取数据超时时间（默认10秒 10000ms） 单位毫秒（ms）
				int soTimeout = 10000;
				httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
				httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

				HttpMethod method = new GetMethod(reqUrl);

				statusCode = httpClient.executeMethod(method);
				// 如果成功，则将ip保存到数据库
				if (statusCode == 200) {
					IPProxy ipProxy = new IPProxy();
					ipProxy.setIp(proxyHost);
					ipProxy.setPort(proxyPort);
					ipProxy.setAddTime(new Date());
					ipProxy.setTimes(0);
					ipProxy.setUsing(0);
					ipProxy.setAvailable(1);
					commonDao.saveEntity(ipProxy);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("%s:%s-->%s\n", proxyHost, proxyPort, statusCode);
		}
	}

	/**
	 * 代理IP有效检测
	 *
	 * @param proxyIp
	 * @param proxyPort
	 * @param reqUrl
	 */
	public void checkProxyIp(String proxyIp, int proxyPort, String reqUrl) {
		Map<String, Integer> proxyIpMap = new HashMap<String, Integer>();
		proxyIpMap.put(proxyIp, proxyPort);
		checkProxyIp(proxyIpMap, reqUrl);
	}

	/**
	 * 将ip代理页面的内容进行解析，得到代理的Map
	 * 
	 * @param content
	 * @param regex
	 * @return
	 */
	public Map<String, Integer> ipList(String content, String regex) {

		Map<String, Integer> proxyIpMap = new HashMap<String, Integer>();
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(content);

		while (ma.find()) {
			proxyIpMap.put(ma.group(1), Integer.parseInt(ma.group(2)));
		}
		return proxyIpMap;
	}
}
