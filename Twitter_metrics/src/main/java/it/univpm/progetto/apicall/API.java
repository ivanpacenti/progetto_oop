/**
 * 
 */
package it.univpm.progetto.apicall;




import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * @author ivan
 * @param <T>
 *
 */
public interface API {
	
	
	abstract JSONArray setData(String url);

	
}
