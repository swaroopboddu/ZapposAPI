/**
 * 
 */
package com.zappos.notification;

import java.util.List;

import com.zappos.domain.Item;
import com.zappos.domain.Product;
import com.zappos.domain.ProductResponse;
import com.zappos.email.EMailNotifier;
import com.zappos.notification.sqllite.SQLLiteConnector;
import com.zappos.rest.RestClient;

/**
 * @author satyaswaroop
 * 
 */
public class NotifierThread implements Runnable {

	RestClient client = new RestClient();

	@Override
	public void run() {
		SQLLiteConnector connector = new SQLLiteConnector();
		connector.createDatabase();
		List<String> ids = connector.getIds();

		ProductResponse res = client.getProduct(ids);
		for (Item p : res.getProduct()) {
			for (Product prod : p.getStyles()) {
				String pec = (prod.getPercentOff().split("%"))[0];
				if ((Integer.parseInt(pec) >= 20)) {
					EMailNotifier.sendNotification(
							connector.selectEmailIds(prod.getStyleId()), prod.getStyleId()
									+ "is found\n" + "To book this product please click on this link "+ prod.getProductUrl());
					connector.delete(prod.getStyleId());
				}
			}
		}

	}

}
