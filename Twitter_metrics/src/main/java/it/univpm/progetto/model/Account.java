package it.univpm.progetto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



/**
 * Classe utilizzata per la deserializzazione di dati contenenti account, ricevuti dalla API di twitter
 * 
 * @param JsonIgnoreProperties ignora le proprietà del file JSON che non corrispondono a nessuno degli attributi di questa classe
 * @author Ivan Pacenti
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
@JsonPropertyOrder({"id", "name","username", "followers", "following","listed","statuses" })
public class Account {
	/**
	 * Significa che in lettura, i valori "id_str" andranno nella variabile "id"
	 */
	@JsonAlias("id_str")
	private String id;
	private String name;
	@JsonAlias("screen_name")
	private String username;
	@JsonAlias("followers_count")
	private int followers;
	private int following;
	/**
	 * rappresenta il numero di liste in cui l'utente è stato inserito
	 */
	@JsonAlias("listed_count")
	private int listed;
	/**
	 * rappresenta il totale dei tweet creati dall'utente, dalla creazione dell'account
	 */
	@JsonAlias("statuses_count")
	private int statuses;
	
	
	/**
	 * costruttore vuoto necessario per il funzionamento della libreria Jackson
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
