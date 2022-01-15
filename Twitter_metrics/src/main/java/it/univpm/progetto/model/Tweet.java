package it.univpm.progetto.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.univpm.progetto.stats.TweetStats;


/**
 * Classe utilizzata per contenere dati dell'oggetto tweet, presente nel JSON ricevuto da twitter
 * 
 * @author Ivan Pacenti
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {
	/**
	 * Oggetto che contiene i tweets.
	 */
	@JsonIgnore
	private List<Tweet> tweets=new ArrayList<>();
	/**
	 * Data di creazione del tweet.<p>
	 * 
	 * Si utilizza l'annotazione <b>@JsonFormat</b> per dire a jackson 
	 * di che formato è la data letta in entrata:
	 * <ul>
	 * <li><b>shape</b> indica che la data in entrata è di tipo stringa.</li>
	 * <li><b>pattern</b> indica la formattazione della stringa da convertire.</li>
	 * <li><b>locale</b> indica che i giorni della settimana e i mesi sono in lingua inglese.</li>
	 * </ul>
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E MMM dd HH:mm:ss Z yyyy",locale = "en")
	private Date created_at;
	/**
	 * Codice identificativo del tweet.
	 */
	@JsonAlias("id_str")
	private String id;
	/**
	 * Testo del tweet, viene ripulito da eventuali hashtag, menzioni o link URL tramite il relativo setter.
	 */
	private String text;
	/**
	 * Engagement del tweet.
	 */
	@JsonIgnore
	private Double engagement;
	/**
	 * Oggetto contenente {@link it.univpm.progetto.model.Hashtag} e {@link it.univpm.progetto.model.Mention} del tweet.
	 */
	private Entity entities=new Entity();
	/**
	 * Numero di retweets del tweet.
	 */
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@JsonAlias("retweet_count")
	private int retweets;
	/**
	 * Numero di likes al tweet.
	 */
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@JsonAlias("favorite_count")
	private int likes;
	/**
	 * Utente che ha creato il tweet.
	 */
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private Account user=new Account();
	/** 
	 * Statistiche del tweet.
	 */
	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	private TweetStats stats;
	/**
	 * Se il tweet è una risposta ad un altro tweet, in questo campo compare il creatore del tweet originale.
	 */
	@JsonAlias("in_reply_to_screen_name")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String reply_to;
	/**
	 * Se il tweet è un retweet, questo campo conterrà il tweet originale.
	 */
	@JsonAlias("retweeted_status")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Tweet original_tweet;
	/**
	 * Se il tweet è una citazione ad un altro tweet, questo campo conterrà il tweet citato.
	 */
	@JsonAlias("quoted_status")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Tweet quoted_tweet;
	/**
	 * Costruttore vuoto per jackson.
	 */
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
	/**
	 * Metodo per inserire nella struttura dati il testo di un tweet.
	 * <p>
	 * Jackson utilizza i setters per la serializzazione dei dati in entrata, 
	 * quindi per un testo dei tweet più chiaro da leggere
	 * vengono rimossi eventuali hashtag, menzioni e link URL.
	 * <p>
	 * I metodi <b>ReplaceAll</b> sostituiscono i caratteri intercettati tramite l'espressione <b>RegEx</b> messa come primo parametro con il secondo parametro,
	 * cioè li cancellano.
	 * 
	 * @param text Testo del tweet.
	 */
	public void setText(String text) {
		this.text=text.replaceAll("@.*? ", "")
				.replaceAll("#[^# ]*","")
				.replaceAll("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]","")
				.replaceAll("\"","")
				.replaceAll("\\n", "");
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
	/**
	 * Metodo che crea e fornisce statistiche del tweet.
	 * <p>
	 * Inizializza un oggetto di tipo <b>DataStats</b> passando dei valori che verranno utilizzati per il calcolo
	 * delle statistiche.
	 * 
	 * @return Le statistiche.
	 */
	public TweetStats getStats() {
		this.stats = new TweetStats(this.getLikes(), this.getRetweets(),entities.getHashtags().size(),
				entities.getMentions().size(),user.getFollowers(),user.getFollowing(),
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
