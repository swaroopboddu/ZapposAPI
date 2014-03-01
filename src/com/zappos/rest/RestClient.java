/**
 * 
 */
package com.zappos.rest;

import java.util.List;

import com.google.gson.Gson;
import com.zappos.domain.ProductResponse;
import com.zappos.domain.SearchResponse;
import com.zappos.restconnector.WebServiceConnector;
import com.zappos.util.PropertiesHandler;

/**
 * @author satyaswaroop
 *
 */
public class RestClient {
	private PropertiesHandler  properties = PropertiesHandler.getProperties("src\\rest.properties");
	
	
	public SearchResponse search(String term,int page)
	{
		String url = properties.getValue("searchurl")+properties.getValue("parameter_term")+term+properties.getValue("parameter_page")+(page);
		String response = WebServiceConnector.getResponse(url);
		Gson gson = new Gson();
		SearchResponse p = gson.fromJson(response, SearchResponse.class);
		return p;
	}
	
	public ProductResponse getProduct(List<String> styleId)
	{
		String url = properties.getValue("producturl")+properties.getValue("parameter_style")+styleId.toString()+properties.getValue("parameter_includes");
		String response = WebServiceConnector.getResponse(url);
		Gson gson = new Gson();
		ProductResponse p = gson.fromJson(response, ProductResponse.class);
		return p;
	}

}
