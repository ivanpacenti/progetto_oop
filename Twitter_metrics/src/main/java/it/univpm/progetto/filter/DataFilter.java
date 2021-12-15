/**
 * 
 */
package it.univpm.progetto.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.model.Tweet;
import it.univpm.progetto.service.DataService;

/**
 * @author ivan
 *
 */
public class DataFilter  {
	
	private static List<Tweet> res=new ArrayList<>();
		
	
	
	public static List<Tweet> searchbyDate (String data) throws ParseException, EmptyCollectionListException 
	{
		res.clear();
		Date date=new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy",Locale.ENGLISH).parse(data);
		if(DataService.getTweets().isEmpty()) throw new EmptyCollectionListException("You have to download some tweets before!");
		else {
			for(Tweet t:DataService.getTweets()) if(t.getCreated_at().after(date)) res.add(t);
			}
		return res;
	}
	
}
