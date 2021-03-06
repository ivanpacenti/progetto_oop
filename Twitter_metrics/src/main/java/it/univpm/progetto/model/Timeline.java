package it.univpm.progetto.model;

import java.util.ArrayList;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Classe utilizzata per contenere i dati delle collezioni
 * 
 * @author Ivan Pacenti
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Timeline {
	/**
	 * Nome della collezione di tweet.
	 */
	private String name;
	/**
	 * Stringa contenente la descrizione della collezione.
	 */
	private String description;
	/**
	 * Codice identificativo della collezione.
	 */
	private String id;
	/**
	 * Variable contenente una lista di tweet.
	 */
	@JsonIgnore
	private List<Tweet> tweets=new ArrayList<>();
	
	/**
	 * Costruttore vuoto per la libreria Jackson.
	 */
	public Timeline() {}
	
	/**
	 * Costruttore della classe, inizializza le variabili.
	 * 
	 * @param name Nome della collezione di tweet.
	 * @param description Stringa contenente la descrizione della collezione.
	 * @param id Codice identificativo della collezione.
	 */
	public Timeline(String name, String description, String id) {
		this.name = name;
		this.description = description;
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the tweets
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}
	/**
	 * @param tweets the tweets to set
	 */
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}
	
	
}
