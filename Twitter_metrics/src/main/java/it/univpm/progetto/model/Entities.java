/**
 * 
 */
package it.univpm.progetto.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author ivan
 *
 */
public class Entities {
	private List<Hashtags> hashtags;

	/**
	 * @param hashtags
	 */
	public Entities(List<Hashtags> hashtags) {
		this.hashtags=hashtags;
	}

	/**
	 * @return the hashtags
	 */
	public List<Hashtags> getHashtags() {
		return hashtags;
	}

	/**
	 * @param hashtags the hashtags to set
	 */
	public void setHashtags(List<Hashtags> hashtags) {
		this.hashtags = hashtags;
	}

	@Override
	public String toString() {
		return " \n[hashtags=" + hashtags + "]";
	}
	
}
