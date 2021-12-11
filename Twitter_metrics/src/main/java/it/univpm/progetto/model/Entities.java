/**
 * 
 */
package it.univpm.progetto.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ivan
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entities {
	
	private List<Hashtags> hashtags=new ArrayList<>();
	@JsonAlias("user_mentions")
	private List<Mentions> mentions=new ArrayList<>();
	
	public Entities() {}
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
	/**
	 * @return the mentions
	 */
	public List<Mentions> getMentions() {
		return mentions;
	}
	/**
	 * @param mentions the mentions to set
	 */
	public void setMentions(List<Mentions> mentions) {
		this.mentions = mentions;
	}

	
	
}
