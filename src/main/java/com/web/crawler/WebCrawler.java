package com.web.crawler;

import java.io.InputStream;

import com.web.crawler.pojo.InternetRequest;
import com.web.crawler.util.JsonUtility;

public class WebCrawler {
	
	public static void main(String args[]) {
		try {
			InputStream inputStream = WebCrawler.class.getResourceAsStream("/internet_1.json");
			InternetRequest request = (InternetRequest) JsonUtility.fromJson(inputStream, InternetRequest.class);
			new URLValidator().validateURL(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
