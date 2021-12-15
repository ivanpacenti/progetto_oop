/**
 * 
 */
package it.univpm.progetto.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import it.univpm.progetto.deprecated.APICall;
import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.filter.DataFilter;
import it.univpm.progetto.model.Account;
import it.univpm.progetto.model.Collection;
import it.univpm.progetto.model.Timeline;
import it.univpm.progetto.model.Tweet;
import it.univpm.progetto.service.DataService;

/**
 * @author ivan
 *
 */
@RestController
public class Controller {
	
	
	/*@Autowired
	private DataService datamodel;
	private DataFilter filter;*/
	
	@GetMapping("/search/accounts")
	public ResponseEntity<List<Account>> 
	searchAccounts(@RequestParam(value="query",required=true) String query) 
	
	{
		
		return new ResponseEntity<List<Account>>(DataService.getAccounts(query),HttpStatus.OK);
		
	}
	

	@GetMapping("/search/collections")
	public ResponseEntity<Map<String,Timeline>> searchCollections
	(@RequestParam(value="id", required=true) String id) 
			throws IllegalArgumentException, IOException, EmptyCollectionListException
	{
		return new ResponseEntity<Map<String,Timeline>>(DataService.getCollections(id),HttpStatus.OK);
	}
	
	@GetMapping("/tweets")
	public ResponseEntity<List<Tweet>> getTweets
	(@RequestParam(value="id",required=true) String id)  throws  IllegalArgumentException, IOException
			
	{
		return new ResponseEntity<List<Tweet>>(DataService.getTweets(id),HttpStatus.OK);
	}
	
	
	@GetMapping("/collections")
	public ResponseEntity<List<Tweet>> getCollections
	(@RequestParam (value="timeline",required=true) String timeline) 
			throws IllegalArgumentException, IOException 
	{
		return new ResponseEntity<List<Tweet>>(DataService.getTimelines(timeline),HttpStatus.OK);
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<Tweet>> getFilter
	(@RequestParam (value="name",required=true) String date) throws ParseException, EmptyCollectionListException 
			
	{	
		
		 return  new ResponseEntity<List<Tweet>>(DataFilter.searchbyDate( date),HttpStatus.OK);
		
	}
}

