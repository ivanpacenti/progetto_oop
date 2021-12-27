package it.univpm.progetto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * Classe utilizzata per la deserializzazione di dati contenenti account, ricevuti dalla API di twitter
 * @param JsonIgnoreProperties ignora le proprietà del file JSON che non corrispondono a nessuno degli attributi di questa classe
 * @author Ivan Pacenti
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public class Account {
	/**
	 * significa che in lettura, i valori "id_str" andranno nella variabile "id"
	 */
	@JsonAlias("id_str")
	private String id;
	private String name;
	@JsonAlias("screen_name")
	private String username;
	@JsonAlias("followers_count")
	private int followers;	
	/**
	 * rappresenta il numero di utenti che l'account segue
	 */
	@JsonAlias("friends_count")
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
	 * @return the friends
	 */
	public int getFriends() {
		return following;
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
	 * @param friends the friends to set
	 */
	public void setFriends(int friends) {
		this.following = friends;
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
