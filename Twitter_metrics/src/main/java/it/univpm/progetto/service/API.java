/**
 * 
 */
package it.univpm.progetto.service;




import java.io.InputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * @author ivan
 * @param <T>
 *
 */
public interface API {
	
	
	
	InputStream getData(String address);

	
}
