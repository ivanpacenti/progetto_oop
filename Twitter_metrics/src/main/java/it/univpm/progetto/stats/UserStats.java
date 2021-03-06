package it.univpm.progetto.stats;

/**
 * Classe astratta che definisce dei metodi per il calcolo dell'engagement
 * e contiene i dati dell'account creatore del tweet.
 * 
 * @author Ivan Pacenti
 *
 */
public abstract class UserStats {
	/**
	 * Username dell'utente.
	 */
	private String username;
	/**
	 * Numero di followers dell'utente.
	 */
	private int followers;
	/**
	 * Utenti che seguono l'account.
	 */
	private int following;
	/**
	 * Numero di liste pubbliche di tweet in cui l'utente compare.
	 */
	private int listed;
	/**
	 * Costruttore della classe.
	 * 
	 * @param username Stringa preceduta da '@', identificativa dell'utente.
	 * @param followers Utenti che seguono gli aggiornamenti dell'account in questione.
	 * @param following Gli utenti che vengono seguiti dell'account.
	 * @param listed Numero di volte che l'account è stato inserito in una lista di tweet.
	 */
	public UserStats(String username, int followers, int following, int listed) {
		this.username = username;
		this.followers = followers;
		this.following = following;
		this.listed = listed;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
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
	 * Metodo astratto, ripreso nella classe {@link it.univpm.progetto.stats.TweetStats}
	 */
	abstract void setEngagement();
	/**
	 * Metodo astratto, ripreso nella classe {@link it.univpm.progetto.stats.TweetStats}
	 * 
	 * @return La percentuale di engagement del tweet.
	 */
	abstract double getEngagement();
}
