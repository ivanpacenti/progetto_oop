package it.univpm.progetto.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.univpm.progetto.stats.DataStats;


/**classe utilizzata per contenere dati dell'oggetto tweet, presente nel JSON ricevuto da twitter
 * @author Ivan Pacenti
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {
	@JsonIgnore
	private List<Tweet> tweets=new ArrayList<>();
	/**fornisce alla libreria jackson il metodo per formattare la data dei tweet in ingresso
	 * @param shape indica che la data in entrata è di tipo stringa
	 * @param pattern indica la formattazione della stringa da convertire
	 * @param locale indica che i giorni della settimana e i mesi sono in lingua inglese
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E MMM dd HH:mm:ss Z yyyy",locale = "en")
	private Date created_at;
	@JsonAlias("id_str")
	private String id;
	private String text;
	@JsonIgnore
	private Double engagement;
	private Entity entities=new Entity();
	/**@param access indica che Jackson,per questo attributo, deve avere un accesso di sola scrittura
	 */
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@JsonAlias("retweet_count")
	private int retweets;
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@JsonAlias("favorite_count")
	private int likes;
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private Account user=new Account();
	/** @param access in questo caso Jackson è in sola lettura
	 */
	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	private DataStats stats;
	/**@param JsonInclude indica la modalità di inclusione dell'attributo nel JSON in uscita
	 * @param NON_NULL indica che verrà inserito solo se non nullo
	 */
	@JsonAlias("in_reply_to_screen_name")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String reply_to;
	@JsonAlias("retweeted_status")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Tweet original_tweet;
	@JsonAlias("quoted_status")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Tweet quoted_tweet;
	
	
	public Tweet() {}
	

	/**
	 * @return the tweets
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}

	/**
	 * @return the created_at
	 */
	
	public Date getCreated_at() {
		return created_at;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the entities
	 */
	public Entity getEntities() {
		return entities;
	}

	/**
	 * @return the retweets
	 */
	public int getRetweets() {
		return retweets;
	}

	/**
	 * @return the likes
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * @param tweets the tweets to set
	 */
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	/**
	 * @param created_at the created_at to set
	 * @throws ParseException 
	 */
	
	public void setCreated_at(Date created_at)  {
		this.created_at = created_at;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**Jackson utilizza i setters per la serializzazione dei dati in entrata, 
	 * quindi per un testo dei tweet più chiaro da leggere
	 * vengono rimossi eventuali hashtag, menzioni e link URL
	 * i metodi ReplaceAll leggono una espressione regex, che indica un insieme di stringhe della stringa @param text
	 * e le cancellano tutte
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text=text.replaceAll("@.*? ", "").replaceAll("#[^# ]*","").replaceAll("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]","").replaceAll("\"","").replaceAll("\\n", "");
		//this.text=text;
	}

	/**
	 * @param entities the entities to set
	 */
	public void setEntities(Entity entities) {
		this.entities = entities;
	}

	/**
	 * @param retweets the retweets to set
	 */
	public void setRetweets(int retweets) {
		this.retweets = retweets;
	}

	/**
	 * @param likes the likes to set
	 */
	public void setLikes(int likes) {
		this.likes = likes;
	}
	/**
	 * @return the retweeted_status
	 */

	/**
	 * @return the user
	 */
	public Account getUser() {
		return user;
	}


	/**
	 * @return the reply_to
	 */
	public String getReply_to() {
		if(reply_to==null) return null;
		else return "@"+reply_to;
	}


	/**
	 * @param reply_to the reply_to to set
	 */
	public void setReply_to(String reply_to) {
		this.reply_to = reply_to;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(Account user) {
		this.user = user;
	}


	/**inizializza un oggetto di tipo DataStats passando dei valori che verranno utilizzati per il calcolo
	 * delle statistiche
	 * @return the stats
	 */
	public DataStats getStats() {
		this.stats = new DataStats(this.getLikes(), this.getRetweets(),entities.getHashtags().size(),
				entities.getMentions().size(),user.getFollowers(),user.getFriends(),
				user.getListed(),user.getUsername());
		
		return stats;
	}


	/**
	 * @return the engagement
	 */
	public Double getEngagement() {
		return stats.getEngagement();
	}


	/**
	 * @return the original_tweet
	 */
	public Tweet getOriginal_tweet() {
		return original_tweet;
	}


	/**
	 * @param original_tweet the original_tweet to set
	 */
	public void setOriginal_tweet(Tweet original_tweet) {
		this.original_tweet = original_tweet;
	}


	/**
	 * @return the quoted_tweet
	 */
	public Tweet getQuoted_tweet() {
		return quoted_tweet;
	}


	/**
	 * @param quoted_tweet the quoted_tweet to set
	 */
	public void setQuoted_tweet(Tweet quoted_tweet) {
		this.quoted_tweet = quoted_tweet;
	}

}
