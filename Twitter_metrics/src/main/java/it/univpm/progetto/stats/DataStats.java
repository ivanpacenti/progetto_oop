/**
 * 
 */
package it.univpm.progetto.stats;

import java.util.List;

import it.univpm.progetto.model.Hashtag;
import it.univpm.progetto.model.Mention;

/**
 * @author ivan
 *
 */
public class DataStats {
	private int likes;
	private int retweets;
	private List<Hashtag> hashtags;
	private List<Mention> mentions;
	/**
	 * @param likes
	 * @param retweets
	 * @param mentions 
	 * @param hashtags 
	 * @param hashtags
	 * @param mentions
	 */
	public DataStats(int likes, int retweets, List<Hashtag> hashtags, List<Mention> mentions) {
		this.likes = likes;
		this.retweets = retweets;
		this.hashtags = hashtags;
		this.mentions = mentions;
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
	 * @return the hashtags
	 */
	public int getHashtags() {
		return hashtags.size();
	}
	/**
	 * @return the mentions
	 */
	public int getMentions() {
		return mentions.size();
	}
	/**
	 * @param hashtags the hashtags to set
	 */
	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}
	/**
	 * @param mentions the mentions to set
	 */
	public void setMentions(List<Mention> mentions) {
		this.mentions = mentions;
	}
	
	
}
