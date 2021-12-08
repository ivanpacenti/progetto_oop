/**
 * 
 */
package it.univpm.progetto.model;

/**
 * @author ivan
 *
 */
public class Hashtags {
	private String text;

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

	@Override
	public String toString() {
		return "\n [text=" + text + "]";
	}
	
}
