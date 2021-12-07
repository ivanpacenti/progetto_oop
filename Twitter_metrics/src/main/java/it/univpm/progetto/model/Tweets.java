/**
 * 
 */
package it.univpm.progetto.model;

import java.util.ArrayList;

/**
 * @author ivan
 *
 */
public class Tweets {
	private String created_at;
	private String id;
	private String text;
	private ArrayList<Entities> entities;
	/**
	 * @param created_at
	 * @param id
	 * @param text
	 * @param entities
	 */
	public Tweets(String created_at, String id, String text, ArrayList<Entities> entities) {
		this.created_at = created_at;
		this.id = id;
		this.text = text;
		this.entities = entities;
	}
	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @return the entities
	 */
	public ArrayList<Entities> getEntities() {
		return entities;
	}
	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @param entities the entities to set
	 */
	public void setEntities(ArrayList<Entities> entities) {
		this.entities = entities;
	}
	
	
	
}
