package it.univpm.progetto.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Classe contente dati della proprietà "mentions" del JSON in entrata.
 * Rappresenta una menzione: contiene il nome dell'utente menzionato,
 * il suo username preceduto da @ e il suo identificativo.
 * 
 * @param JsonIgnoreProperties ignora le proprietà del file JSON che non corrispondono a nessuno degli attributi di questa classe
 * @author Ivan Pacenti
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mention {
	
	private String name;
	@JsonAlias("screen_name")
	private String username;
	@JsonAlias("id_str")
	private String id;
	
	public Mention() {}

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
	 * @return the id
	 */
	public String getId() {
		return id;
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
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
