package it.univpm.progetto.tests;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.progetto.model.APICall;

/**
 * 
 */

/**
 * @author ivan
 *
 */
class TweetsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	
	APICall apicall=new APICall();
	System.out.println(apicall.list("1304170778"));
	}
	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link it.univpm.progetto.model.APICall#list(java.lang.String)}.
	 */
	@Test
	void testList() {
		fail("Not yet implemented");
	}

}
