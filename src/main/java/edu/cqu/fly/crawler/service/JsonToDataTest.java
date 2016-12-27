package edu.cqu.fly.crawler.service;

import edu.cqu.fly.erawler.domain.OutputData;

public class JsonToDataTest {
	public static void main(String [] args){
		TaskService taskService=new TaskServiceImpl();
		String json="{\"productActualID\":\"18069002307\",\"productName\":\"重庆特产桥头水煮肉片调料 四川美食特产 底料\"}";
		OutputData data=taskService.getOutputDataFromJson(json);
		
		System.out.println(data.getProductActualID());
		
		
	}

}
