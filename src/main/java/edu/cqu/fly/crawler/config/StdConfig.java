package edu.cqu.fly.crawler.config;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StdConfig implements iConfig {

	private String filePath;

	private Document document;
	private XPath xpath = XPathFactory.newInstance().newXPath();

	public StdConfig(String filePath) {
		this.filePath = filePath;
		this.loadFromFile(this.filePath);
	}

	public boolean loadFromFile(String filePath) {
		// TODO Auto-generated method stub
		// 解析文件，生成document对象
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = builder.parse(new File(filePath));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取XPath路径所表示的值
	 * 
	 * @param elementPath
	 * @return
	 * @throws Exception
	 */
	public String getXpathText(String elementPath) {
		try {
			String XpathText = (String) xpath.evaluate(elementPath, document, XPathConstants.STRING);
			return XpathText;
		} catch (Exception e) {
			return null;
		}

	}

	public Node getNodeObj(String elementPath) throws Exception {
		// 获取节点对象
		Node bookWeb = (Node) xpath.evaluate(elementPath, document, XPathConstants.NODE);
		System.out.println(bookWeb.getNodeName());

		System.out.println("===========================================================");
		return bookWeb;
	}

	/**
	 * 获取节点集合
	 * 
	 * @param elementPath
	 * @return
	 * @throws Exception
	 */
	public NodeList getNodeList(String elementPath) throws Exception {
		NodeList nodes = (NodeList) xpath.evaluate(elementPath, document, XPathConstants.NODESET);
		return nodes;
	}
	/*
	 * @Override public Map<String, String> getDataObjects(String platform)
	 * throws Exception { // TODO Auto-generated method stub String elementPath
	 * = "dataParseConfig/websites/website[@name='" + platform +
	 * "']/dataobjects/*"; NodeList nodes = getNodeList(elementPath);
	 * Map<String, String> platformObjMap = new HashMap<String, String>(); for
	 * (int i = 0; i < nodes.getLength(); i++) { Node node = nodes.item(i);
	 * platformObjMap.put(platform, node.getNodeName()); }
	 * 
	 * return platformObjMap; }
	 * 
	 */

}
