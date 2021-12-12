/**
 * 
 */
package it.univpm.progetto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ivan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Timelines {
	private String name;
	private String description;
	private String id;
	
	public Timelines() {}
	
	
	/**
	 * @param name
	 * @param description
	 * @param id
	 */
	public Timelines(String name, String description, String id) {
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
	
	
}
