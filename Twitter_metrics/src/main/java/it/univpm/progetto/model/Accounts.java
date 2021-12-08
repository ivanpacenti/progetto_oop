/**
 * 
 */
package it.univpm.progetto.model;

import java.util.ArrayList;

/**
 * @author ivan
 *
 */
public class Accounts {
	private String id;
	private String name;
	private String screen_name;
	private int followers_count;
	private int friends_count;
	private int listed_count;
	private int statuses_count;
	private String profile_image_url;
	
	
	
	/**
	 * @param id
	 * @param name
	 * @param screen_name
	 * @param followers_count
	 * @param friends_count
	 * @param listed_count
	 * @param statuses_count
	 * @param profile_image_url
	 */
	public Accounts(String id, String name, String screen_name, int followers_count, int friends_count,
			int listed_count, int statuses_count, String profile_image_url) {
		this.id = id;
		this.name = name;
		this.screen_name = screen_name;
		this.followers_count = followers_count;
		this.friends_count = friends_count;
		this.listed_count = listed_count;
		this.statuses_count = statuses_count;
		this.profile_image_url = profile_image_url;
	}
	
	public String getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the screen_name
	 */
	public String getScreen_name() {
		return screen_name;
	}
	/**
	 * @return the followers_count
	 */
	public int getFollowers_count() {
		return followers_count;
	}
	/**
	 * @return the friends_count
	 */
	public int getFriends_count() {
		return friends_count;
	}
	/**
	 * @return the listed_count
	 */
	public int getListed_count() {
		return listed_count;
	}
	/**
	 * @return the statuses_count
	 */
	public int getStatuses_count() {
		return statuses_count;
	}

	/**
	 * @return the profile_image_url
	 */
	public String getProfile_image_url() {
		return profile_image_url;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param screen_name the screen_name to set
	 */
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	/**
	 * @param followers_count the followers_count to set
	 */
	public void setFollowers_count(int followers_count) {
		this.followers_count = followers_count;
	}
	/**
	 * @param friends_count the friends_count to set
	 */
	public void setFriends_count(int friends_count) {
		this.friends_count = friends_count;
	}
	/**
	 * @param listed_count the listed_count to set
	 */
	public void setListed_count(int listed_count) {
		this.listed_count = listed_count;
	}
	/**
	 * @param statuses_count the statuses_count to set
	 */
	public void setStatuses_count(int statuses_count) {
		this.statuses_count = statuses_count;
	}
	/**
	 * @param profile_image_url the profile_image_url to set
	 */
	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	@Override
	public String toString() {
		return "Accounts [id=" + id + ", name=" + name + ", followers_count=" + followers_count + "]";
	}
	
	
	
}
