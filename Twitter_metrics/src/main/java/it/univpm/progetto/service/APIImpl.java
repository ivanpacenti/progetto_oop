/**
 * 
 */
package it.univpm.progetto.service;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;




/**
 * @author ivan
 * 
 *
 */
public class APIImpl implements API{
	
	private InputStream is;

	public InputStream getData(String address)
	{
		
		try {
			
			URL url=new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			is = conn.getInputStream();
		} catch (Exception e) 
			{
				e.printStackTrace();
			}
		return is;
		}

	

	

}
