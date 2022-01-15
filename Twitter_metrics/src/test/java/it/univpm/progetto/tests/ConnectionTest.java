package it.univpm.progetto.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.progetto.service.DataService;


/**
 * Classe che testa l'effettivo download di dati.<p>
 * Metodo di test per {@link it.univpm.progetto.service.APIImpl#getData(java.lang.String)}.
 * @author Ivan Pacenti
 *
 */
class ConnectionTest {
	/**
	 * Oggetto della classe che si sta testando.
	 */
	DataService service=new DataService();
	/**
	 * Test di connessione all'API di twitter, verifica che 
	 * i dati scaricati non siano nulli.
	 * 
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		service.getTweets("7014762",200,false,false);
	}

	/**
	 * Verifica che i tweets della classe DataService non siano nulli.
	 */
	@Test
	void testGetData() {
		assertNotEquals(service.getTweets(), "");
	}
}
