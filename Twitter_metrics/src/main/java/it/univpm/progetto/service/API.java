/**
 * 
 */
package it.univpm.progetto.service;




import java.io.InputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progetto.exceptions.StreamException;


/**
 * @author ivan
 * @param <T>
 *
 */
public interface API {
	
	
	
	InputStream getData(String address) throws StreamException ;

	
}
