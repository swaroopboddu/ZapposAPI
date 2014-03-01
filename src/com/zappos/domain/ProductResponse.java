/**
 * 
 */
package com.zappos.domain;

import java.util.List;

/**
 * @author satyaswaroop
 *
 */
public class ProductResponse {
	private List<Item> product;
	public List<Item> getProduct() {
		return product;
	}
	public void setProduct(List<Item> product) {
		this.product = product;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	private String statusCode;

}
