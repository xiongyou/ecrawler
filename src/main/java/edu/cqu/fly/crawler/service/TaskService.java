package edu.cqu.fly.crawler.service;

import edu.cqu.fly.erawler.domain.GeneratedKey;
import edu.cqu.fly.erawler.domain.OutputData;

public interface TaskService extends SystemService{

	public GeneratedKey getATask(String clientId);
	
	public GeneratedKey scheduleATask(String clientId);
	
	public GeneratedKey scheduleAProxyTask(String clientID);
	
	public OutputData getOutputDataFromJson(String json);


}
