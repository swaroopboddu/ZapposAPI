/**
 * 
 */
package com.zappos.domain.ui;

import com.zappos.domain.Product;
import com.zappos.restconnector.WebServiceConnector;

/**
 * @author satyaswaroop
 *
 */
public class UIProduct {
	private String imageFile;
	private String styleId;
	private String price;
	private String originalPrice;
	private String productUrl;
	private String colorId;
	private String productName;
	private String brandName;
	private String percentOff;
	private String productId;
	
	public UIProduct(Product p)
	{
		this.setBrandName(p.getBrandName());
		this.setColorId(p.getColorId());

		this.setOriginalPrice(p.getOriginalPrice());
		this.setPrice(p.getPrice());
		this.setProductId(p.getProductId());
		this.setProductName(p.getProductName());
		this.setProductUrl(p.getProductUrl());
		this.setStyleId(p.getStyleId());
		this.setPercentOff(p.getPercentOff());
		if(styleId!=null)
		this.setImageFile(WebServiceConnector.getImage(p.getThumbnailImageUrl(), styleId));
		
	}
	
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String string) {
		this.imageFile = string;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getPercentOff() {
		return percentOff;
	}
	public void setPercentOff(String percentOff) {
		this.percentOff = percentOff;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
	
}
