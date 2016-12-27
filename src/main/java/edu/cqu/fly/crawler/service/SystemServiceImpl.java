package edu.cqu.fly.crawler.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("systemService")
@Transactional
public class SystemServiceImpl extends CommonServiceImpl implements SystemService{

	private final static Logger logger = Logger.getLogger(SystemServiceImpl.class);
	public void addLog(String logContent, Short loglevel, Short operatetype) {
		logger.info(logContent+"----"+loglevel);
	}
}
