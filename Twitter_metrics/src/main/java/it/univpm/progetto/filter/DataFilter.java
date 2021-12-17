/**
 * 
 */
package it.univpm.progetto.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
public final class DataFilter implements Filter<Tweet, Object> {
	
	private static List<Tweet> result=new ArrayList<>();
	private static FilterUtils<Tweet> utils;
		
	
	
	/**
	 * @param result
	 * 
	 */
	public DataFilter(List<Tweet> result) {
		DataFilter.result = result;
		DataFilter.utils=new FilterUtils<Tweet>();
		
	}
	


	public static List<Tweet> searchbyDate (String begindate,String enddate) 
			throws ParseException, EmptyCollectionListException 
	{
		result.clear();
		
		if(DataService.getTweets().isEmpty()) throw new EmptyCollectionListException("You have to download some tweets before!");
		if((begindate!=null))
		{
			
			Date parsedbegindate=new SimpleDateFormat("dd MM yy",Locale.ENGLISH).parse(begindate);
			for(Tweet t:DataService.getTweets())
			{
				if(t.getCreated_at().after(parsedbegindate)) result.add(t);
			}
		}if((enddate!=null))
		{
			Date parsedenddate=new SimpleDateFormat("dd MM yy",Locale.ENGLISH).parse(enddate);
			for(Tweet t:result)
			{
				if(t.getCreated_at().after(parsedenddate)) result.remove(t);
			}
		}
		
		return result;
	}

	

	/**
	 * @return the result
	 */
	public static List<Tweet> getResult() {
		return result;
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
		DataFilter.result = result;
	}



	/**
	 * @param utils the utils to set
	 */
	public void setUtils(FilterUtils<Tweet> utils) {
		DataFilter.utils = utils;
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}

	
	public  List<Tweet> filterField(String fieldName, String operator, String str) {
		Object value;
		if(isNumeric(str)) 
			{
				value=Double.parseDouble(str);
			}
		else value=str;
		return (List<Tweet>) utils.select(getResult(), fieldName, operator, value);
	}
	
}
