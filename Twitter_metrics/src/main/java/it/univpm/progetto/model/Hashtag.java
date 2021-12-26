package it.univpm.progetto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ivan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hashtag {
	
	private String text;
	
	public Hashtag() {}
	/**
	 * @param text
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
