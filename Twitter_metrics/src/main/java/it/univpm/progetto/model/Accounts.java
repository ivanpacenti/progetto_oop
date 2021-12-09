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
	private String username;
	private int followers;
	private int friends;
	private int listed;
	private int statuses;
	private String profile_image_url;
	
	
	
	/**
	 * @param id
	 * @param name
	 * @param username
	 * @param followers
	 * @param friends
	 * @param listed
	 * @param statuses
	 * @param profile_image_url
	 */
	public Accounts(String id, String name, String username, int followers, int friends,
			int listed, int statuses, String profile_image_url) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.followers = followers;
		this.friends = friends;
		this.listed = listed;
		this.statuses = statuses;
		this.profile_image_url = profile_image_url;
	}
	
	public String getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getname() {
		return name;
	}
	/**
	 * @return the username
	 */
	public String getusername() {
		return username;
	}
	/**
	 * @return the followers
	 */
	public int getfollowers() {
		return followers;
	}
	/**
	 * @return the friends
	 */
	public int getfriends() {
		return friends;
	}
	/**
	 * @return the listed
	 */
	public int getlisted() {
		return listed;
	}
	/**
	 * @return the statuses
	 */
	public int getstatuses() {
		return statuses;
	}

	/**
	 * @return the profile_image_url
	 */
	/*public String getProfile_image_url() {
		return profile_image_url;
	}*/
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * @param username the username to set
	 */
	public void setusername(String username) {
		this.username = username;
	}
	/**
	 * @param followers the followers to set
	 */
	public void setfollowers(int followers) {
		this.followers = followers;
	}
	/**
	 * @param friends the friends to set
	 */
	public void setfriends(int friends) {
		this.friends = friends;
	}
	/**
	 * @param listed the listed to set
	 */
	public void setlisted(int listed) {
		this.listed = listed;
	}
	/**
	 * @param statuses the statuses to set
	 */
	public void setstatuses(int statuses) {
		this.statuses = statuses;
	}
	/**
	 * @param profile_image_url the profile_image_url to set
	 */
	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	@Override
	public String toString() {
		return "\n [id=" + id + ", name=" + name + ", username=" + username + ", followers="
				+ followers + ", friends=" + friends + ", listed=" + listed
				+ ", statuses=" + statuses + "]";
	}

	
	
	
	
}
