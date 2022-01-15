package it.univpm.progetto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe contenente i dati della proprietà "hashtag" del JSON in entrata.
 *  
 * @author Ivan Pacenti
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hashtag {
	
	/**
	 * Contiene il testo di un hashtag, viene aggiunto un <b>#</b> per far capire subito all'utente il significato del campo.
	 */
	private String text;
	/**
	 * Costruttore vuoto per jackson.
	 */
	public Hashtag() {}
	/**
	 * @param text Testo dell'hashtag.
	 */
	public Hashtag(String text) {
		this.text = text;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		if(text==null) return null;
		else return "#"+text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	
}
