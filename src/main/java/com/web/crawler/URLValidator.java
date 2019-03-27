package com.web.crawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.validator.routines.UrlValidator;

import com.web.crawler.pojo.InternetRequest;
import com.web.crawler.pojo.InternetResponse;
import com.web.crawler.pojo.Page;
import com.web.crawler.util.JsonUtility;

public class URLValidator {
	
	private ExecutorService executor;
	
	public URLValidator() {
		executor = Executors.newCachedThreadPool();
	}

	public void validateURL(InternetRequest request) throws IOException {
		Callable<Boolean> task = () ->{
			processInternetPages(request);
			return true;
		};
		executor.submit(task);
	}

	private void processInternetPages(InternetRequest request) {
		
		List<String> linksToProcess = new ArrayList<>();
		List<String> skippedLink = getDuplicateLink(request, linksToProcess);
		List<String> error = new ArrayList<>();
		InternetResponse response = new InternetResponse();
		response.setSkipped(skippedLink);
		
		for (String link : linksToProcess) {
			if (!isURLValid(link)) {
				error.add(link);
			}
		}
		
		linksToProcess.removeAll(error);
		response.setSucess(linksToProcess);
		response.setError(error);
		try {
			System.out.println(JsonUtility.toJson(response));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private boolean isURLValid(String link) {
		URL url;
		try {
			url = new URL(link);
		
		HttpURLConnection hrc = (HttpURLConnection) url.openConnection();
		return hrc.getResponseCode()==200;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private List<String> getDuplicateLink(InternetRequest request, List<String> linksToProcess) {
		List<String> skippedLinks = new ArrayList<String>();
		for(Page page : request.getPages()) {
			for(String link : page.getLinks()) {
				if(linksToProcess.contains(link) && !skippedLinks.contains(link)) {
					skippedLinks.add(link);
				} else {
					linksToProcess.add(link);
				}
			}
		}
		return skippedLinks;
	}

}
