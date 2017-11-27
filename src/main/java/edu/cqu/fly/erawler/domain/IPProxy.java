package edu.cqu.fly.erawler.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xiongyou1701
 *IP代理实体类
 */
@Entity
@Table(name="ip_proxy")
public class IPProxy {
	/**
	 * IP编号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ip_proxy_id")
	private int ipProxyId;
	
	@Column(name="ip")
	private String ip; //IP地址
	
	@Column(name="port")
	private int port; //端口号
	
	@Column(name="add_time")
	private Date addTime; //添加代理到数据库的时间
	
	@Column(name="used_time")
	private Date usedTime; //记录上一次被使用的时间
	
	@Column(name="in_use")
	private int using=0; //是否正在使用中
	
	@Column(name="available")
	private int available;//是否可用
	
	@Column(name="times")
	private int times;//已经使用的次数
    /*
	@Column(name="types")
	private int types; //类型

	@Column(name="protocol")
	private int protocal;//协议

	@Column(name="country")
	private String country;//国家

	@Column(name="area")
	private String area;//区域

	@Column(name="update_time")
	private Date updateTime;//更新时间

	@Column(name="speed")
	private double speed;//响应速度
    */

	public Integer getIpProxyId() {
		return ipProxyId;
	}

	public void setIpProxyId(Integer ipProxyId) {
		this.ipProxyId = ipProxyId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}

	public Integer getUsing() {
		return using;
	}

	public void setUsing(Integer using) {
		this.using = using;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}
	
	public String toString(){
		return "{\"ip\":"+"\""+this.ip+"\","+"\"port\":"+"\""+this.port+"\"}";
	}

}
