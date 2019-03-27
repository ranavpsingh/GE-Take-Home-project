package com.web.crawler.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Page {
	
	private String address;
	private List<String> links;
	
	@JsonProperty("address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonProperty("links")
	public List<String> getLinks() {
		return links;
	}
	public void setLinks(List<String> links) {
		this.links = links;
	}

}
