package it.univpm.progetto.stats;

import java.math.BigDecimal;

import java.math.RoundingMode;


/**Classe per il calcolo delle statistiche dei singoli tweet
 * @author Ivan Pacenti
 *
 */
public class DataStats {
	/*dati dall'account del tweet
	 */
	private String username;
	private int followers;
	private int following;
	/*numero di liste pubbliche di tweet in cui l'utente compare
	 */
	private int listed;
	
	/*statistiche del tweet
	 */
	private int likes;
	private int retweets;
	private int hashtags;
	private int mentions;
	private double engagement;
	
	/**costruttore che inzializza le variabili
	 * @param likes numero di likes del tweet
	 * @param retweets numero di retweet
	 * @param hashtags totale degli hashtag
	 * @param mentions totale delle menzioni
	 * @param followers totale dei followers dell'utente che ha creato il tweet
	 * @param following totale di account che l'utente "segue"
	 * @param listed totale di liste pubbliche in cui l'utente compare
	 * @param username username dell'utente 
	 */
	public DataStats(int likes, int retweets, int hashtags, int mentions, int followers, int following, int listed,
			String username) {
		this.likes = likes;
		this.retweets = retweets;
		this.hashtags = hashtags;
		this.mentions = mentions;
		this.followers = followers;
		this.following = following;
		this.listed = listed;
		this.username = username;
		/*metodo che calcola l'engagement del tweet
		 */
		setEngagement();
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
	
	/**metodo che calcola l'engagement del tweet
	 * l'engagement è una percentuale che rappresenta il rapporto tra interazioni effettuate su un tweet e 
	 * il totale dei followers dell'utente
	 * per un valore più preciso ho utilizzato la classe BigDecimal che permette di definire i numeri dopo la virgola
	 * e l'arrotondamento
	 */
	public void setEngagement()
	{
		double tot=this.likes+this.retweets;
		if(tot==0||this.followers==0) this.engagement=0;
		else {
			double tmp=(tot/this.followers)*100;
			this.engagement=BigDecimal.valueOf(tmp).setScale(5, RoundingMode.HALF_UP).doubleValue();
		}

	}

}
