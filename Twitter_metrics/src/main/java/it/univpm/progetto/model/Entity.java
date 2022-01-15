package it.univpm.progetto.model;

import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Classe necessaria a contenere dati della proprietà "entities" del JSON in entrata.
 * <p>
 * Contiene una lista di hashtag e menzioni.
 * </p>
 * 
 * @author Ivan Pacenti
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {
	/**
	 * Contiene una lista di hashtag.
	 */
	private List<Hashtag> hashtags=new ArrayList<>();
	/**
	 * Contiene una lista di "menzioni", delle citazioni di altri account.
	 */
	@JsonAlias("user_mentions")
	private List<Mention> mentions=new ArrayList<>();
	/**
	 * Costruttore vuoto per il funzionamento di Jackson.
	 */
	public Entity() {}
	/**
	 * @param hashtags hashtags
	 */
	public Entity(List<Hashtag> hashtags) {
		this.hashtags=hashtags;
	}
	/**
	 * @return the hashtags
	 */
	public List<Hashtag> getHashtags() {
		return hashtags;
	}
	/**
	 * @param hashtags the hashtags to set
	 */
	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}
	/**
	 * @return the mentions
	 */
	public List<Mention> getMentions() {
		return mentions;
	}
	/**
	 * @param mentions the mentions to set
	 */
	public void setMentions(List<Mention> mentions) {
		this.mentions = mentions;
	}

	
	
}
