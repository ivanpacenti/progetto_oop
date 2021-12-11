/**
 * 
 */
package it.univpm.progetto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progetto.deprecated.APICall;

import it.univpm.progetto.model.Accounts;
import it.univpm.progetto.model.Tweets;

/**
 * @author ivan
 *
 */
@RestController
public class Controller {
	

	
	
	
	@GetMapping("/search")
	public ArrayList<Accounts> search(@RequestParam(value="query") String query)
	throws Exception
	{
		Accounts accounts=new Accounts(query);
		return accounts.getAsd();		
		
		
	}
	
	/*@GetMapping("/tweets")
	public ResponseEntity<ArrayList<Tweets>> list(@RequestParam(value="id", defaultValue = "1304170778")String id)
	{
		Tweets tweets=new Tweets(id);
		return new ResponseEntity<>(tweets.getTweets()),HttpStatus.OK);
	}*/
}
