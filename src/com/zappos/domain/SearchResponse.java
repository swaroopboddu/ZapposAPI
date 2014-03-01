/**
 * 
 */
package com.zappos.domain;

import java.util.List;

/**
 * @author satyaswaroop
 *
 */
public class SearchResponse {
	
	private String statusCode;
	private int totalResultCount;
	private int currentResultCount;
	public int getCurrentResultCount() {
		return currentResultCount;
	}
	public void setCurrentResultCount(int currentResultCount) {
		this.currentResultCount = currentResultCount;
	}
	public int getTotalResultCount() {
		return totalResultCount;
	}
	public void setTotalResultCount(int totalResultCount) {
		this.totalResultCount = totalResultCount;
	}
	private List<Product> results;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public List<Product> getResults() {
		return results;
	}
	public void setResults(List<Product> results) {
		this.results = results;
	}

}
