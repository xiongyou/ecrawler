package edu.cqu.fly.crawler.service;

public interface SystemService extends CommonService{
	/**
	 * 日志添加
	 */
	public void addLog(String LogContent, Short loglevel,Short operatetype);
}
