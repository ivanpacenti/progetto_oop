package it.univpm.progetto.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Classe che testa l'effettivo download di dati
 * 
 * @author Ivan Pacenti
 *
 */
class ConnectionTest {
	DataService service=new DataService();
	/**
	 * Test di connessione all'API di twitter, verifica che 
	 * i dati scaricati non siano nulli
	 * @throws java.lang.Exception
	 * 
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		service.getTweets("7014762",200,false,false);
	}

	/**
	 * Test method for {@link it.univpm.progetto.service.APIImpl#getData(java.lang.String)}.
	 * Verifica che i tweets della classe DataService non siano nulli
	 */
	@Test
	void testGetData() {
		assertNotEquals(service.getTweets(), "");
	}
}
