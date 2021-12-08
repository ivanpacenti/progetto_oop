/**
 * 
 */
package it.univpm.progetto.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author ivan
 *
 */
public class Tweets {
	private String created_at;
	private String id;
	private String text;
	private Entities entities;
	private ArrayList<Hashtags> hashtags=new ArrayList<Hashtags>();
	private int retweet_count;//Number of times this Tweet has been retweeted
	private int favorite_count;//Nullable. Indicates approximately how many times this Tweet has been liked by Twitter users
	/**
	 * @param created_at
	 * @param id
	 * @param text
	 * @param entities
	 */
	public Tweets(String created_at, String id, String text, JSONObject entitiesobj,int retweet_count,int favorite_count) {
		this.created_at = created_at;
		this.id = id;
		this.text = text;
		JSONArray hashtag_json=entitiesobj.getJSONArray("hashtags");
		for (int i = 0; i < hashtag_json.length(); i++) {
			JSONObject obj = hashtag_json.getJSONObject(i);
			hashtags.add(new Hashtags(obj.getString("text")));
		}
		this.entities=new Entities(hashtags);
		this.retweet_count=retweet_count;
		this.favorite_count=favorite_count;
		
	}
	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return the retweet_count
	 */
	public int getRetweet_count() {
		return retweet_count;
	}
	/**
	 * @return the favorite_count
	 */
	public int getFavorite_count() {
		return favorite_count;
	}
	/**
	 * @param retweet_count the retweet_count to set
	 */
	public void setRetweet_count(int retweet_count) {
		this.retweet_count = retweet_count;
	}
	/**
	 * @param favorite_count the favorite_count to set
	 */
	public void setFavorite_count(int favorite_count) {
		this.favorite_count = favorite_count;
	}
	@Override
	public String toString() {
		return "\nTweets \n[created_at=" + created_at + ",\n id=" + id + ",\n text=" + text + ",\n entities=" + entities
				  + ",\n retweet_count=" + retweet_count + ",\n favorite_count=" + favorite_count
				+  "\n"+hashtags+"]";
	}
	
	
	
	
	
	
}
