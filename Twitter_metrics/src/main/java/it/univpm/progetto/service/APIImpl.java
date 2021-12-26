package it.univpm.progetto.service;

import java.io.IOException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import it.univpm.progetto.exceptions.InputStreamException;




/**
 * @author ivan
 * 
 *
 */
public class APIImpl implements API{
	
	private InputStream is;

	public InputStream getData(String address) throws InputStreamException
	{
		
		try {
			
			URL url=new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			is = conn.getInputStream();
			 
		} catch (IOException e) 
			{
			throw new InputStreamException("The user has a private profile or is non-existent");
			}
		return is;
		}

	

	

}
