package it.univpm.progetto.model;


/**
 * Contiene i metadati del JSON in output
 * 
 * @author Ivan Pacenti
 *
 */
public class Metadata {
	/**
	 * nome della proprietà JSON
	 */
	String alias;
	/**
	 * descrizione della proprietà JSON
	 */
	String sourceField;
	/**
	 * tipo della proprietà JSON
	 */
	String type;
	/**
	 * @param alias indica il nome del parametro
	 * @param sourceField indica la descrizione
	 * @param type indica il tipo
	 */
	public Metadata(String alias, String sourceField, String type) {
		this.alias = alias;
		this.sourceField = sourceField;
		this.type = type;
	}
	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @return the sourceField
	 */
	public String getSourceField() {
		return sourceField;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * @param sourceField the sourceField to set
	 */
	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
