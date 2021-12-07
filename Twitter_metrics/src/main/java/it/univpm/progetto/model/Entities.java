/**
 * 
 */
package it.univpm.progetto.model;

import java.util.ArrayList;

/**
 * @author ivan
 *
 */
public class Entities {
	private ArrayList<Hashtags> hashtags;

	/**
	 * @param hashtags
	 */
	public Entities(ArrayList<Hashtags> hashtags) {
		this.hashtags = hashtags;
	}

	/**
	 * @return the hashtags
	 */
	public ArrayList<Hashtags> getHashtags() {
		return hashtags;
	}

	/**
	 * @param hashtags the hashtags to set
	 */
	public void setHashtags(ArrayList<Hashtags> hashtags) {
		this.hashtags = hashtags;
	}
	
}
