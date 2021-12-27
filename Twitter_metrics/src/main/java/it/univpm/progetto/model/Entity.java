package it.univpm.progetto.model;

import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Classe necessaria a contenere dati della proprietà "entities" del JSON in entrata
 * contiene una lista di hashtag e menzioni
 * 
 * @param JsonIgnoreProperties ignora le proprietà del file JSON che non corrispondono a nessuno degli attributi di questa classe
 * @author Ivan Pacenti
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {
	/*
	 * rappresenta una lista di hashtag
	 */
	private List<Hashtag> hashtags=new ArrayList<>();
	/*
	 * rappresenta una lista di menzioni
	 */
	@JsonAlias("user_mentions")
	private List<Mention> mentions=new ArrayList<>();
	
	public Entity() {}
	/**
	 * @param hashtags
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
