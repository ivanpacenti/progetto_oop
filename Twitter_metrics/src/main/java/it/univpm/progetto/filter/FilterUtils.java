/**
 * 
 */
package it.univpm.progetto.filter;

import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.model.Tweet;

/**
 * @author ivan
 *
 */
public   class FilterUtils<T> {
	
	public static boolean check(Object value, String operator, Object th) {
		if (th instanceof Number && value instanceof Number) {	
			Double thC = ((Number)th).doubleValue();
			Double valuec = ((Number)value).doubleValue();
			if (operator.equals("=="))
				return valuec.equals(thC);
			else if (operator.equals(">"))
				return valuec > thC;
			else if (operator.equals("<"))
				return valuec < thC;
		}else if(th instanceof String && value instanceof String)
			return value.equals(th);
		return false;		
	}
	
	public Collection<T> select(Collection<T> src, String fieldName, String operator, Object value) {
		Collection<T> out = new ArrayList<T>();
		for(T item:src) {
			try {
				Method m = item.getClass().getMethod("get"+fieldName.substring(0, 1).toUpperCase()+
						fieldName.substring(1),null);
				try {
					Object tmp = m.invoke(item);
					if(FilterUtils.check(tmp, operator, value))
						out.add(item);
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
			} catch (NoSuchMethodException e) {
				
				e.printStackTrace();
			} catch (SecurityException e) {
				
				e.printStackTrace();
			}					
		}
		return out;
	}
	public  List<Tweet> selectDate(List<Tweet> tweets, String from, String to)  throws ParseException, EmptyCollectionListException
	{
	List<Tweet> result=new ArrayList<>(tweets);
	Iterator<Tweet> iterator=result.iterator();
	//if(tweets.isEmpty()) throw new EmptyCollectionListException("You have to download some tweets before!");
	if((from!=null))
	{
		
		Date parsedbegindate=new SimpleDateFormat("dd MM yy",Locale.ENGLISH).parse(from);
		while(iterator.hasNext())
		{
			Tweet t=iterator.next();
			if(t.getCreated_at().before(parsedbegindate)) iterator.remove();
		}
	}if((to!=null))
	{
		Date parsedenddate=new SimpleDateFormat("dd MM yy",Locale.ENGLISH).parse(to);
		iterator=result.iterator();
		while(iterator.hasNext())
		{
			Tweet t=iterator.next();
			if(t.getCreated_at().after(parsedenddate)) iterator.remove();
		}
	}
	return result;
	}
	
	public  List<Tweet> selectHour(List<Tweet> tweets, String from, String to)  
			throws ParseException, EmptyCollectionListException
	{
	List<Tweet> result=new ArrayList<>(tweets);
	Iterator<Tweet> iterator=result.iterator();
	Calendar getdate = Calendar.getInstance(Locale.US);
	Calendar midnight = Calendar.getInstance(Locale.US);
	//if(tweets.isEmpty()) throw new EmptyCollectionListException("You have to download some tweets before!");
	if((from!=null))
	{
		int hour=Integer.parseInt(from);
		
		//Date parsedbeginhour=new SimpleDateFormat("HH").parse(from);
		
		while(iterator.hasNext())
		{	
			Tweet t=iterator.next();
			getdate.setTime(t.getCreated_at());
			getdate.set(Calendar.HOUR_OF_DAY, hour);
			getdate.set(Calendar.MINUTE, 00);
			getdate.set(Calendar.SECOND, 00);
			if(t.getCreated_at().before(getdate.getTime())) iterator.remove();
		}
	}if((to!=null))
	{
		int hour=Integer.parseInt(to);
		iterator=result.iterator();
		while(iterator.hasNext())
		{
			Tweet t=iterator.next();
			getdate.setTime(t.getCreated_at());
			getdate.set(Calendar.HOUR_OF_DAY, hour);
			getdate.set(Calendar.MINUTE, 00);
			getdate.set(Calendar.SECOND, 00);
			if(t.getCreated_at().after(getdate.getTime())) iterator.remove();
		}
	}
	
	return result;
	}

	public Map<String,Object> analyze(List<Tweet> tmp) {
		Double tot=0.0;
		
		for(Tweet t:tmp) 
			{
				tot+=t.getEngagement();
			}
		Double average=tot/tmp.size();
		tot=0.0;
		for(Tweet t:tmp) tot+=Math.pow((t.getEngagement()-average), 2);
		Double variance=tot/tmp.size();
		Tweet min = tmp
			      .stream()
			      .min(Comparator.comparing(Tweet::getEngagement))
			      .orElseThrow(NoSuchElementException::new);
		Tweet max = tmp
			      .stream()
			      .max(Comparator.comparing(Tweet::getEngagement))
			      .orElseThrow(NoSuchElementException::new);
		Map<String,Object> map=new LinkedHashMap<>();
		map.put("Tweets analized", tmp.size());
		map.put("Average engagement", average);
		map.put("Variance of engagement", variance);
		map.put("Higher engagement", max.getEngagement());
		map.put("Lower engagement", min.getEngagement());
		return map;
	}

	
	
}

