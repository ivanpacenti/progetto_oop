/**
 * 
 */
package it.univpm.progetto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ivan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hashtags {
	
	private String text;
	
	public Hashtags() {}
	/**
	 * @param text
	 */
	public Hashtags(String text) {
		this.text = text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	
}
