package it.univpm.progetto.stats;

import java.math.BigDecimal;

import java.math.RoundingMode;


/**
 * Classe per il calcolo delle statistiche dei singoli tweet.
 *  
 * @author Ivan Pacenti
 *
 */
public class TweetStats extends UserStats{
	
	/**
	 * Numero di likes del tweet.
	 */
	private int likes;
	/**
	 * Numero di retweets del tweet.
	 */
	private int retweets;
	/**
	 * Numero di hashtag utilizzati nel tweet.
	 */
	private int hashtags;
	/**
	 * Numero di menzioni utilizzate nel tweet.
	 */
	private int mentions;
	/**
	 * Percentuale di engagement raggiunta dal tweet.
	 */
	private double engagement;
	
	/**
	 * Costruttore che inzializza le variabili.
	 * 
	 * @param likes Numero di likes del tweet.
	 * @param retweets Numero di retweets del tweet.
	 * @param hashtags Numero di hashtag utilizzati nel tweet.
	 * @param mentions Numero di menzioni utilizzate nel tweet.
	 * @param followers Numero di persone che seguono gli aggiornamenti dell'account.
	 * @param following Numero di persone che l'account segue.
	 * @param listed Numero di liste pubbliche di tweet in cui l'account compare.
	 * @param username Username dell'utente. 
	 */
	public TweetStats(int likes, int retweets, int hashtags, int mentions, int followers, int following, int listed,
			String username) {
		super(username, followers, following, listed);
		this.likes = likes;
		this.retweets = retweets;
		this.hashtags = hashtags;
		this.mentions = mentions;
		/*metodo che calcola l'engagement del tweet
		 */
		setEngagement();
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
	 * @return the engagement
	 */
	public double getEngagement() {
		return engagement;
	}
	/**
	 * Metodo che calcola l'engagement del tweet.
	 * <p>
	 * L'engagement è una percentuale che rappresenta il rapporto tra interazioni effettuate su un tweet e 
	 * il totale dei followers dell'utente.
	 * Per un valore più preciso si è utilizzato la classe BigDecimal che permette di definire i numeri dopo la virgola
	 * e l'arrotondamento.
	 */
	public void setEngagement()
	{
		double tot=this.likes+this.retweets;
		if(tot==0||getFollowers()==0) this.engagement=0;
		else {
			double tmp=(tot/getFollowers())*100;
			this.engagement=BigDecimal.valueOf(tmp).setScale(5, RoundingMode.HALF_UP).doubleValue();
		}
	}
}
