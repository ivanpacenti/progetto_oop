/**
 * 
 */
package it.univpm.progetto.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author ivan
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {
	
	private List<Hashtag> hashtags=new ArrayList<>();
	@JsonAlias("user_mentions")
	private List<Mention> mentions=new ArrayList<>();
	
	public Entity() {}
	/**
	 * @param hashtags
	 */
	public Entity(List<Hashtag> hashtags) {
		this.hashtags=hashtags;
	}

	/**
	 * @return the hashtags
	 */
	public List<Hashtag> getHashtags() {
		return hashtags;
	}

	/**
	 * @param hashtags the hashtags to set
	 */
	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}
	/**
	 * @return the mentions
	 */
	public List<Mention> getMentions() {
		return mentions;
	}
	/**
	 * @param mentions the mentions to set
	 */
	public void setMentions(List<Mention> mentions) {
		this.mentions = mentions;
	}

	
	
}