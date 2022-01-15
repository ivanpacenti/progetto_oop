package it.univpm.progetto.filter;

import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.exceptions.InvalidDateException;
import it.univpm.progetto.exceptions.InvalidFilterException;
import it.univpm.progetto.exceptions.InvalidHourException;
import it.univpm.progetto.model.Tweet;


/**
 * Classe contenente metodi per il filtraggio, implementa l'interfaccia Utils.
 * 
 * @author Ivan Pacenti
 *
 */
public class FilterUtils implements Utils{
	
	/**
	 * Metodo che controlla se un oggetto verifica o meno la condizione del filtro.
	 * 
	 * @param value Valore del tweet da controllare. 
	 * @param operator Operatore che specifica il metodo di verifica.
	 * @param th Valore effettivo da utilizzare pe il controllo.
	 * @return Valore booleano, true o false a seconda dell'esito del filtraggio.
	 */
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
	
	/** Metodo che si occupa del controllo dei tweet.
	 * 
	 * @param src Collezione di tweet presa in input, da filtrare.
	 * @param fieldName Campo da filtrare.
	 * @param operator Operatore per filtrare.
	 * @param value Valore da utilizzare per la verifica.
	 * @return Una lista di tweet che hanno superato le condizioni del filtro.
	 * @throws InvalidFilterException Eccezione lanciata in caso di invalidità del campo da filtrare, inserito in input.
	 */
	public List<Tweet> select(List<Tweet> src, String fieldName, String operator, Object value) throws InvalidFilterException {
		List<Tweet> out = new ArrayList<>();
		for(Tweet item:src) {
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
				throw new InvalidFilterException("No such attribute exists");
			} catch (SecurityException e) {

				e.printStackTrace();
			}					
		}
		return out;
	}
	
	/**
	 * Metodo che filtra i tweet per data.
	 * 
	 * @param tweets Lista di tweet da filtrare.
	 * @param from Stringa contenente una data per filtrare.
	 * @param to Stringa contenete un'altra data per filtrare.
	 * @return Una lista di tweet creati nell'intervallo di tempo definito dalle due date, 
	 *         in assenza di date da filtrare la lista resta immutata,
	 *         in caso sia presente solo la data "from" verranno restituiti i tweet creati dopo,
	 *         in caso sia presente solo la data "to" verranno restituiti solo i tweet creati prima,
	 *         in caso siano presenti sia "from" che "to" verranno restituiti i tweet creati tra le due date.
	 * @throws ParseException Eccezione relativa ad eventuali problemi con valori di tipo Data.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote.
	 * @throws InvalidDateException Eccezione lanciata in caso di formati invalidi dei parametri "from" e "to" in input.
	 */
	@Override
	public List<Tweet> selectDate(List<Tweet> tweets, String from, String to) 
			throws ParseException, EmptyCollectionListException, InvalidDateException
	{
		List<Tweet> result=new ArrayList<>(tweets);
		Iterator<Tweet> iterator=result.iterator();
		if((from!=null))
		{

			try{
				Date parsedbegindate=new SimpleDateFormat("dd MM yy",Locale.ENGLISH).parse(from);

				while(iterator.hasNext())
				{
					Tweet t=iterator.next();
					if(t.getCreated_at().before(parsedbegindate)) iterator.remove();
				}
			}catch(ParseException e)
			{
				throw new InvalidDateException("Please insert a valid date of the format: dd mm yy");
			}
		}if((to!=null))
		{
			try {
				Date parsedenddate=new SimpleDateFormat("dd MM yy",Locale.ENGLISH).parse(to);
				iterator=result.iterator();
				while(iterator.hasNext())
				{
					Tweet t=iterator.next();
					if(t.getCreated_at().after(parsedenddate)) iterator.remove();
				}
			}catch(ParseException e)
			{
				throw new InvalidDateException("Please insert a valid date of the format: dd mm yy");
			}
		}
		return result;
	}
	
	/**
	 * Metodo che filtra i tweet in base all'ora.
	 * 
	 * @param tweets Lista di tweet da filtrare.
	 * @param from Stringa contenente un'ora per filtrare.
	 * @param to Stringa contenete un'altra ora per filtrare.
	 * @return Una lista di tweet creati nell'intervallo di tempo definito dalle due ore, 
	 *         in assenza di ore da filtrare la lista resta immutata,
	 *         in caso sia presente solo il valore "from", verranno restituiti i tweet creati dopo, fino alla mezzanotte dello stesso giorno,
	 *         in caso sia presente solo l'ora "to", verranno restituiti solo i tweet creati prima, dalla mezzanotte dello stesso giorno fino all'ora in questione,
	 *         in caso siano presenti sia "from" che "to" verranno restituiti i tweet creati tra le due ore.
	 * @throws ParseException Eccezione relativa ad eventuali problemi con valori di tipo Data.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote.
	 * @throws InvalidHourException Eccezione lanciata in caso di formati invalidi dei parametri "from" e "to" in input.
	 */
	@Override
	public  List<Tweet> selectHour(List<Tweet> tweets, String from, String to)  
			throws ParseException, EmptyCollectionListException, InvalidHourException
	{

		List<Tweet> result=new ArrayList<>(tweets);
		Iterator<Tweet> iterator=result.iterator();
		Calendar getdate = Calendar.getInstance(Locale.US);
		if((from!=null))
		{
			int hour=Integer.parseInt(from);
			if(hour<0||hour>24) throw new InvalidHourException("Please insert a valid hour of the format: hh");
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
			if(hour<0||hour>24) throw new InvalidHourException("Please insert a valid hour of the format: hh");
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

	/**
	 * Metodo che calcola media, varianza, engagement più alto e più basso di una lista di tweet.
	 * 
	 * @param tmp Lista di tweet da analizzare.
	 * @return Tabella con statistiche, contenente anche il numero di tweet analizzati.
	 *
	 */
	@Override
	public Map<String,Object> analyze(List<Tweet> tmp) 
	{
		/**
		 * Totale utilizzato per calcolare la media.
		 */
		Double tot=0.0;
		/**
		 * Media.
		 */
		Double average=0.0;
		/**
		 * Varianza
		 */
		Double variance=0.0;
		/**
		 * Engagement massimo.
		 */
		Double maxengagement=0.0;
		/**
		 * Engagement minimo.
		 */
		Double minengagement=0.0;
		/**
		 * In caso di lista vuota di tweet da analizzare, 
		 * si è voluto stampare una tabella vuota invece di lanciare un'eccezione
		 */
		if(!tmp.isEmpty()) 
		{
			for(Tweet t:tmp) 
			{
				tot+=t.getEngagement();
			}
			average=tot/tmp.size();
			tot=0.0;
			for(Tweet t:tmp) tot+=Math.pow((t.getEngagement()-average), 2);
			variance=tot/tmp.size();
			minengagement = tmp //lista di tweets presa come parametro
					.stream() //metodo che ha come ritorno un flusso di oggetti sequenziali
					.min(Comparator.comparing(Tweet::getEngagement)) //min seleziona l'elemento con valore più basso, selezionato con Comparator(tramite getter getEngagement)
					.orElseThrow(NoSuchElementException::new) //eccezione lanciata in casi non sia possibile ottenere un minimo
					.getEngagement(); //ritorna l'engagement del tweet con engagement minimo, preso dallo stream sopra
			maxengagement = tmp
					.stream()
					.max(Comparator.comparing(Tweet::getEngagement))//come sopra ma stavolta si seleziona il massimo
					.orElseThrow(NoSuchElementException::new)
					.getEngagement();

		}
		/**
		 * Tabella di valori da restituire in output.<p>
		 * Si utilizza una LinkedHashMap poichè si vuole mantenere ordine sulle righe dei valori
		 */
		Map<String,Object> map=new LinkedHashMap<>();
		map.put("Tweets analized", tmp.size());
		map.put("Average engagement", average);
		map.put("Variance of engagement", variance);
		map.put("Higher engagement", maxengagement);
		map.put("Lower engagement", minengagement);
		return map;
	}

}