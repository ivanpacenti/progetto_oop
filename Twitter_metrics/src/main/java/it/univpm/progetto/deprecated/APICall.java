/**
 * 
 */
package it.univpm.progetto.deprecated;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.univpm.progetto.model.Accounts;
import it.univpm.progetto.model.Tweets;

/**
 * @author ivan
 * 
 *
 */
public class APICall {

	public String array(URL url) {
		try {
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
				 
				 return informationString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	 

}