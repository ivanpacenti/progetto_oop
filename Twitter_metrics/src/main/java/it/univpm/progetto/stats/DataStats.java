/**
 * 
 */
package it.univpm.progetto.stats;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import it.univpm.progetto.model.Hashtag;
import it.univpm.progetto.model.Mention;

/**
 * @author ivan
 *
 */
public class DataStats {
	private String username;
	private int likes;
	private int retweets;
	private int hashtags;
	private int mentions;
	private int followers;
	private int following;
	private int listed;//The number of public lists that this user is a member of
	private double engagement;
	
	/**
	 * @param likes
	 * @param retweets
	 * @param hashtags
	 * @param mentions
	 * @param followers
	 * @param friends
	 * @param listed
	 * @param username
	 */
	public DataStats(int likes, int retweets, int hashtags, int mentions, int followers, int friends, int listed,
			String username) {
		this.likes = likes;
		this.retweets = retweets;
		this.hashtags = hashtags;
		this.mentions = mentions;
		this.followers = followers;
		this.following = friends;
		this.listed = listed;
		this.username = username;
		double tot=this.likes+this.hashtags+this.mentions+this.retweets;
		if(tot==0) this.engagement=0;
		else {
			double tmp=tot/this.followers*100;
			this.engagement=BigDecimal.valueOf(tmp).setScale(3, RoundingMode.HALF_UP).doubleValue();
			}
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @return the likes
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * @return the retweets
	 */
	public int getRetweets() {
		return retweets;
	}

	/**
	 * @return the hashtags
	 */
	public int getHashtags() {
		return hashtags;
	}

	/**
	 * @return the mentions
	 */
	public int getMentions() {
		return mentions;
	}

	/**
	 * @return the followers
	 */
	public int getFollowers() {
		return followers;
	}

	/**
	 * @return the following
	 */
	public int getFollowing() {
		return following;
	}

	/**
	 * @return the listed
	 */
	public int getListed() {
		return listed;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param likes the likes to set
	 */
	public void setLikes(int likes) {
		this.likes = likes;
	}

	/**
	 * @param retweets the retweets to set
	 */
	public void setRetweets(int retweets) {
		this.retweets = retweets;
	}

	/**
	 * @param hashtags the hashtags to set
	 */
	public void setHashtags(int hashtags) {
		this.hashtags = hashtags;
	}

	/**
	 * @param mentions the mentions to set
	 */
	public void setMentions(int mentions) {
		this.mentions = mentions;
	}

	/**
	 * @param followers the followers to set
	 */
	public void setFollowers(int followers) {
		this.followers = followers;
	}

	/**
	 * @param following the following to set
	 */
	public void setFollowing(int following) {
		this.following = following;
	}

	/**
	 * @param listed the listed to set
	 */
	public void setListed(int listed) {
		this.listed = listed;
	}

	/**
	 * @return the engagement
	 */
	public double getEngagement() {
		return engagement;
	}

	
	
	


}
