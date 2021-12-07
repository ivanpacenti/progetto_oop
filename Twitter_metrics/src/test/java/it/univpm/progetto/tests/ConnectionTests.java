/**
 * 
 */
package it.univpm.progetto.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.progetto.model.APICall;

/**
 * @author ivan
 *
 */
class ConnectionTests {
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		APICall apicall=new APICall();
		apicall.searchapi("prova");
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link it.univpm.progetto.model.APICall#searchapi(java.lang.String)}.
	 */
	@Test
	void testSearchapi() {
		
	}

}
