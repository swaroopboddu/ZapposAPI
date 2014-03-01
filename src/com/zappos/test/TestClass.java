/**
 * 
 */
package com.zappos.test;

import java.util.ArrayList;
import java.util.List;

import com.zappos.domain.Product;
import com.zappos.domain.ProductResponse;
import com.zappos.domain.SearchResponse;
import com.zappos.rest.RestClient;
import com.zappos.restconnector.WebServiceConnector;

/**
 * @author satyaswaroop
 *
 */
public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Product p = Product.getInstance("{\"styleId\":\"2276732\",\"price\":\"$199.95\",\"originalPrice\":\"$199.95\",\"productUrl\":\"http://www.zappos.com/product/8149427/color/3\",\"colorId\":\"3\",\"productName\":\"Mutiny\",\"brandName\":\"DC\",\"thumbnailImageUrl\":\"http://www.zappos.com/images/z/2/2/7/6/7/3/2276732-t-THUMBNAIL.jpg\",\"percentOff\":\"0%\",\"productId\":\"8149427\"}"); 
		System.out.println(p.getBrandName());
		System.out.println(p.convertToJSON());*/
		
		//System.out.println(new WebServiceConnector().getResponse("http://api.zappos.com/Search?key=a73121520492f88dc3d33daf2103d7574f1a3166&limit=100&term=boot&page=1"));
		RestClient s = new RestClient();
		SearchResponse res = s.search("boots",1);
		System.out.println(res.getResults().get(0).toString());
		List<String> list = new ArrayList<String>();
		for(Product p : res.getResults())
		{
			list.add(p.getStyleId());
		}
		ProductResponse product = s.getProduct(list);
		System.out.println(product.getProduct().toString());
	}

}
