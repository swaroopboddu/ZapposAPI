/**
 * 
 */
package com.zappos.restconnector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author satyaswaroop
 * 
 */
public class WebServiceConnector {

	public static String getResponse(String urlString) {
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.connect();
			if (connection.getResponseCode() == 200) {
				java.io.BufferedReader rd = new java.io.BufferedReader(
						new java.io.InputStreamReader(
								connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line = null;
				while ((line = rd.readLine()) != null) {
					response.append(line + '\n');
				}
				connection.disconnect();
				rd.close();
				return (response != null) ? response.toString() : "No Response";
			} else if (connection.getResponseCode() == 401) {
				return "Unauthorized";
			} else
				return "BadRequest";
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "BadRequest";

	}

	public static String getImage(String urlString, String destination) {
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "image/jpeg");
			connection.connect();
			if (connection.getResponseCode() == 200) {
				InputStream in = connection.getInputStream();
				OutputStream os = new FileOutputStream(destination);
				// int fileLength = connection.getContentLength();
				byte[] buf = new byte[1024];
				int length;
				while ((length = in.read(buf)) != -1) {
					os.write(buf, 0, length);
				}
				in.close();
				os.close();
				return destination;
			} else if (connection.getResponseCode() == 401) {
				return "Unauthorized";
			} else
				return "BadRequest";
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "BadRequest";
	}

}
