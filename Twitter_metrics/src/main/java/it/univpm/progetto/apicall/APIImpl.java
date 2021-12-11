/**
 * 
 */
package it.univpm.progetto.apicall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;




/**
 * @author ivan
 * 
 *
 */
public class APIImpl  {
	
	private String data;
	
	public  APIImpl(String address)
	{
		try {
			
			URL url=new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			// Check if connection is made
			int responseCode = conn.getResponseCode();
			
			// if 200 -> OK
			if (responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			} else {

				String informationString = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					informationString += scanner.nextLine();
				}
				// Close the scanner
				scanner.close();
				//JSONObject obj = (JSONObject) JSONValue.parseWithException(informationString);
				this.data= informationString;
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	

	

}
