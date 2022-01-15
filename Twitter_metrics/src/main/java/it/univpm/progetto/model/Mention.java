package it.univpm.progetto.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Classe contente dati della proprietà "mentions" del JSON in entrata.
 * <p>
 * Rappresenta una menzione: contiene il nome dell'utente menzionato,
 * il suo username preceduto da <b>@</b> e il suo identificativo.</p>
 * 
 * @author Ivan Pacenti
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mention {
	
	/**
	 * Nome dell'utente menzionato.
	 */
	private String name;
	/**
	 * Username dell'utente menzionato.
	 */
	@JsonAlias("screen_name")
	private String username;
	/**
	 * Codice identificativo dell'utente menzionato.
	 */
	@JsonAlias("id_str")
	private String id;
	/**
	 * Costruttore vuoto per jackson.
	 */
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
