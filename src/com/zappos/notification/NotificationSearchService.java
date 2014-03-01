/**
 * 
 */
package com.zappos.notification;

import java.util.ArrayList;
import java.util.List;

import com.zappos.domain.Product;
import com.zappos.domain.SearchResponse;
import com.zappos.domain.ui.UIProduct;
import com.zappos.notification.sqllite.SQLLiteConnector;
import com.zappos.rest.RestClient;

/**
 * @author satyaswaroop
 * 
 */
public class NotificationSearchService {

	private String query;
	private int pageNo;
	private SearchResponse response;
	private RestClient client = new RestClient();
	private SQLLiteConnector connector = new SQLLiteConnector();

	public NotificationSearchService(String query) {
		connector.createDatabase();
		this.query = query;
		this.pageNo = 1;
	}

	public int getTotalPages() {
		return (int) Math.ceil((double) response.getTotalResultCount()
				/ (double) 10);
	}

	public List<UIProduct> getNextPage() {
		pageNo++;
		if(pageNo<=getTotalPages())
		return search();
		else
			return null;

	}

	public List<UIProduct> search() {
		response = client.search(query, pageNo);
		List<UIProduct> result = new ArrayList<UIProduct>();
		for (Product p : response.getResults()) {
			result.add(new UIProduct(p));
		}
		return result;

	}

	public List<UIProduct> getPreviousPage() {
		if(pageNo>0)
		pageNo--;
		return search();

	}

	public void save(String styleId, String emailId) {
		// TODO Auto-generated method stub
		if (emailId != null && styleId != null && !emailId.equals("")
				&& !styleId.equals(""))
			connector.insert(emailId, styleId);

	}

}
