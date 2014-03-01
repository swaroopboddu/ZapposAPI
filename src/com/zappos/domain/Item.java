/**
 * 
 */
package com.zappos.domain;

import java.util.List;


/**
 * @author satyaswaroop
 *
 */
public class Item {
	List<Product> styles;

	public List<Product> getStyles() {
		return styles;
	}

	public void setStyles(List<Product> styles) {
		this.styles = styles;
	}

	@Override
	public String toString() {
		return "Item [styles=" + styles + "]";
	}

	
	
}
