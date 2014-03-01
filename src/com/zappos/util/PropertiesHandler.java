/**
 * 
 */
package com.zappos.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author satyaswaroop
 *
 */
public class PropertiesHandler {
	
	Properties prop;
	
	private PropertiesHandler()
	{
		super();
	}
	public static PropertiesHandler getProperties(String path)
	{
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(path));
			PropertiesHandler handler = new PropertiesHandler();
			handler.prop = prop;
			return handler;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getValue(String key)
	{
		String value = prop.getProperty(key);
		return value;
	}
	
	

}
