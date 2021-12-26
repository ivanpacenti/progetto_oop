package it.univpm.progetto.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.progetto.service.APIImpl;
import it.univpm.progetto.service.DataService;


/**classe che testa l'effettivo download di dati
 * @author Ivan Pacenti
 *
 */
class ConnectionTest {

	/**
	 * @throws java.lang.Exception
	 * Test di connessione all'API di twitter, verifica che 
	 * i dati scaricati non siano nulli
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		DataService.getTweets("7014762",200,false,false);
	}
	

	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		
	}

	/**
	 * Test method for {@link it.univpm.progetto.service.APIImpl#getData(java.lang.String)}.
	 * Verifica che i tweets della classe DataService non siano nulli
	 */
	@Test
	void testGetData() {
		/*verifica che i dati ricevuti non siano nulli
		 */
		assertNotEquals(DataService.getTweets(), "");
	}

}
