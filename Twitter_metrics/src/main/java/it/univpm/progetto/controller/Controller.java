/**
 * 
 */
package it.univpm.progetto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import it.univpm.progetto.deprecated.APICall;
import it.univpm.progetto.exceptions.NullQueryException;
import it.univpm.progetto.model.Accounts;
import it.univpm.progetto.model.Collections;
import it.univpm.progetto.model.Timelines;
import it.univpm.progetto.model.Tweets;

/**
 * @author ivan
 *
 */
@RestController
public class Controller {
	

	
	
	
	@GetMapping("/search/accounts")
	public ResponseEntity<List<Accounts>> 
	searchAccounts(@RequestParam(value="query",required=true) String query) 
	
	{
		if(query.equals("")) throw new NullQueryException(HttpStatus.BAD_REQUEST, "Please insert query value");
		Accounts accounts=new Accounts(query);
		return new ResponseEntity<List<Accounts>>(accounts.getaccounts(),HttpStatus.OK);
		
	}
	

	@GetMapping("/search/collections")
	public ResponseEntity<Map<String,Timelines>> searchCollections
	(@RequestParam(value="id", defaultValue = "1304170778") String id) 
			throws IllegalArgumentException, IOException
	{
		Collections collection=new Collections(id);
		return new ResponseEntity<Map<String,Timelines>>(collection.getMap(),HttpStatus.OK);
	}
	
	@GetMapping("/tweets")
	public ResponseEntity<List<Tweets>> getTweets
	(@RequestParam(value="id", defaultValue = "1304170778") String id) 
			throws IllegalArgumentException, IOException
	{
		Tweets tweets=new Tweets(id);
		return new ResponseEntity<List<Tweets>>(tweets.getTweets(),HttpStatus.OK);
	}
	
	
	@GetMapping("/collections")
	public ResponseEntity<List<Tweets>> getCollections
	(@RequestParam(value="timeline", defaultValue = "custom-1125432419803377665") String timeline) 
			throws IllegalArgumentException, IOException
	{
		Timelines tml=new Timelines(timeline);
		return new ResponseEntity<List<Tweets>>(tml.getTweets(),HttpStatus.OK);
	}
	
}

