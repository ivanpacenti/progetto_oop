package it.univpm.progetto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



/**
 * Classe utilizzata per la deserializzazione di dati contenenti account, ricevuti dalla API di twitter.
 * 
 * <p>Vengono utilizzate alccune delle annotazioni messe a disposizione della libreria Jackson.
 * Per esempio:
 * <ul>
 * <li><b>@JsonIgnoreProperties</b> ignora le proprietà del file JSON che non corrispondono a nessuno degli attributi di questa classe.</li>
 * <li><b>@JsonPropertyOrder</b> è usato per ordinare l'output delle JSON properties.</li>
 * <li><b>@JsonProperty</b> dice a jackson che deve far riferimento a quel getter o setter considerandolo come scritto tra parentesi.</li>
 * </ul>
 * 
 * @author Ivan Pacenti
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
@JsonPropertyOrder({"id", "name","username", "followers", "following","listed","statuses" }) 
public class Account {
	/**
	 * Identificativo dell'account.
	 */
	@JsonAlias("id_str")
	private String id;
	/**
	 * Nome dell'account.
	 */
	private String name;
	/**
	 * Username dell'account, quello che sul sito web di twitter è preceduto da <b>@</b>.
	 */
	@JsonAlias("screen_name")
	private String username;
	/**
	 * Numero di persone che seguono gli aggiornamenti dell'account.
	 */
	@JsonAlias("followers_count")
	private int followers;
	/**
	 * Numero di persone che l'account segue.
	 */
	private int following;
	/**
	 * Numero di liste pubbliche di tweet in cui l'account compare.
	 */
	@JsonAlias("listed_count")
	private int listed;
	/**
	 * Totale dei tweet creati dall'account, dalla sua creazione ad oggi.
	 */
	@JsonAlias("statuses_count")
	private int statuses;
	
	
	/**
	 * Costruttore vuoto necessario per il funzionamento della libreria Jackson.
	 */
	public Account() {}

	/**
	 * @return the id
	 */
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
	 * Si aggiunge una <b>@</b> all'output,
	 * per distinguere l'username dal nome più generale
	 * @return the username
	 */
	public String getUsername() {
		return "@"+username;
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
    @JsonProperty("following")
	public int getFollowing() {
		return following;
	}
	/**
	 * @param following the following to set
	 */
	@JsonProperty("friends_count")
	public void setFollowing(int following) {
		this.following = following;
	}
	/**
	 * @return the listed
	 */
	public int getListed() {
		return listed;
	}
	/**
	 * @return the statuses
	 */
	public int getStatuses() {
		return statuses;
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
	 * @param listed the listed to set
	 */
	public void setListed(int listed) {
		this.listed = listed;
	}
	/**
	 * @param statuses the statuses to set
	 */
	public void setStatuses(int statuses) {
		this.statuses = statuses;
	}	
}
