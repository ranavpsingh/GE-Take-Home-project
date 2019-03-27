package com.web.crawler.pojo;

import java.util.List;

public class InternetResponse {
	
	List<String> sucess;
	List<String> skipped;
	List<String> error;
	
	public List<String> getSucess() {
		return sucess;
	}
	public void setSucess(List<String> sucess) {
		this.sucess = sucess;
	}
	public List<String> getSkipped() {
		return skipped;
	}
	public void setSkipped(List<String> skipped) {
		this.skipped = skipped;
	}
	public List<String> getError() {
		return error;
	}
	public void setError(List<String> error) {
		this.error = error;
	}

}
