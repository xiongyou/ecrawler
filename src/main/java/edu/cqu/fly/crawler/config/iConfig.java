package edu.cqu.fly.crawler.config;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;


public interface iConfig {

	/**
	 * 解析文件，生成document对象
	 * 
	 * @param fileName
	 * @throws XPathExpressionException
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @pdOid 249405cf-6cec-45c2-8dcd-3a6d5fea4ff8
	 */
	boolean loadFromFile(String fileName);
	
	public String getXpathText(String elementPath) throws Exception;


}
