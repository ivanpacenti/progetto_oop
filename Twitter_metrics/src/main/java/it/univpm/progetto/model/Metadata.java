package it.univpm.progetto.model;


/**
 * Contiene i metadati del JSON in output.
 * 
 * @author Ivan Pacenti
 *
 */
public class Metadata {
	
	/**
	 * Stringa contenente il nome del parametro.
	 */
	private String alias;
	/**
	 * Indica la descrizione del parametro.
	 */
	private String sourceField;
	/**
	 * Indica il tipo di dato che costituisce il parametro.
	 */
	private String type;
	/**
	 * Costruttore della classe, inizializza le variabili.
	 * 
	 * @param alias Stringa contenente il nome del parametro.
	 * @param sourceField Indica la descrizione del parametro.
	 * @param type Indica il tipo di dato che costituisce il parametro.
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
