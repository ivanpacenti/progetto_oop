/**
 * 
 */
package it.univpm.progetto.filter;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.model.Tweet;
import it.univpm.progetto.service.DataService;

/**
 * @author ivan
 *
 */
public class DataFilter implements Filter<Tweet, Object> {
	
	private  List<Tweet> tweets=new ArrayList<>();
	private  List<Tweet> tmp=new ArrayList<>();
	private  FilterUtils<Tweet> utils;
		
	
	
	/**
	 * @param result
	 * 
	 */
	public DataFilter(List<Tweet> tweets) {
		this.tweets = tweets;
		this.utils=new FilterUtils<Tweet>();
		
	}

	/**
	 * @return the utils
	 */
	public FilterUtils<Tweet> getUtils() {
		return utils;
	}

	/**
	 * @param result the result to set
	 */
	public  void setResult(List<Tweet> result) {
	}
	

	/**
	 * @return the tweets
	 */
	public  List<Tweet> getTweets() {
		return tweets;
	}


	/**
	 * @param tweets the tweets to set
	 */
	public  void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}


	/**
	 * @param utils the utils to set
	 */
	public void setUtils(FilterUtils<Tweet> utils) {
		this.utils = utils;
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}

	@Override //togliere se non funzia
	public  List<Tweet> filterField(String fieldName, String operator, String str) {
		Object value;
		if(isNumeric(str)) 
			{
				value=Double.parseDouble(str);
			}
		else value=str;
		return (List<Tweet>) utils.select(getTweets(), fieldName, operator, value);
	}
	
	public Map<String, Object> searchbyDate (String from_day,String to_day, String from_hour, String to_hour) 
			throws ParseException, EmptyCollectionListException 
	{
		
		
		this.tmp=utils.selectHour
				(utils.selectDate(getTweets(), from_day, to_day), from_hour, to_hour);
		Map<String,Object> map=utils.analyze(this.tmp);
		Map<String,Object> prova=new LinkedHashMap<>();
		prova.put("analisys", map);
		prova.put("tweets", tmp);
		return prova;
		//return tmp;
	}
	
	/*public Map<String, Object> searchbyHour(String from,String to)
			throws ParseException, EmptyCollectionListException 
	{
		
		this.tmp=utils.selectHour(getTweets(), from, to);
		Map<String,Object> map=utils.analyze(this.tmp);
		Map<String,Object> prova=new LinkedHashMap<>();
		prova.put("analisys", map);
		prova.put("tweets", tmp);
		return prova;
		//return tmp;
	}*/
	
	/*public Map<String, Object> analyze()
			throws ParseException, EmptyCollectionListException 
	{
		
		
		return utils.analyze(this.tmp);
	}*/
}
