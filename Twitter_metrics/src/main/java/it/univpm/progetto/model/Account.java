/**
 * 
 */
package it.univpm.progetto.model;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import it.univpm.progetto.service.APIImpl;


/**
 * @author ivan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
	@JsonAlias("id_str")
	private String id;
	private String name;
	@JsonAlias("screen_name")
	private String username;
	@JsonAlias("followers_count")
	private int followers;
	@JsonAlias("friends_count")
	private int friends;
	@JsonAlias("listed_count")
	private int listed;
	@JsonAlias("statuses_count")
	private int statuses;
	@JsonIgnore
	private String profile_image_url;
	@JsonIgnore
	private static final String ACCOUNTS_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/users/search.json?q=";
	@JsonIgnore
	List<Account> accounts=new ArrayList<>();
	
	
	public Account() {}
	
	/**
	 * @throws JsonMappingException 
	 * @throws JsonProcessingException 
	 * 
	 */	
	/*public Account(String query) 
	{
		
		String url=ACCOUNTS_API_URL+query.replaceAll(" ", "%20");
		APIImpl call=new APIImpl(url);
		ObjectMapper mapper=new ObjectMapper();
		try {
			accounts = Arrays.asList(mapper.readValue(call.getData(), Account[].class));
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}*/

	/**
	 * @param id
	 * @param name
	 * @param username
	 * @param followers
	 * @param friends
	 * @param listed
	 * @param statuses
	 * @param profile_image_url
	 */
	public Account(String id, String name, String username, int followers, int friends,
			int listed, int statuses, String profile_image_url) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.followers = followers;
		this.friends = friends;
		this.listed = listed;
		this.statuses = statuses;
		this.profile_image_url = profile_image_url;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return "@"+username;
	}
	/**
	 * @return the followers
	 */
	public int getFollowers() {
		return followers;
	}
	/**
	 * @return the friends
	 */
	public int getFriends() {
		return friends;
	}
	/**
	 * @return the listed
	 */
	public int getListed() {
		return listed;
	}
	/**
	 * @return the statuses
	 */
	public int getStatuses() {
		return statuses;
	}
	/**
	 * @return the profile_image_url
	 */
	public String getProfile_image_url() {
		return profile_image_url;
	}
	/**
	 * @return the accountsApiUrl
	 */
	public static String getAccountsApiUrl() {
		return ACCOUNTS_API_URL;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param followers the followers to set
	 */
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	/**
	 * @param friends the friends to set
	 */
	public void setFriends(int friends) {
		this.friends = friends;
	}
	/**
	 * @param listed the listed to set
	 */
	public void setListed(int listed) {
		this.listed = listed;
	}
	/**
	 * @param statuses the statuses to set
	 */
	public void setStatuses(int statuses) {
		this.statuses = statuses;
	}
	/**
	 * @param profile_image_url the profile_image_url to set
	 */
	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}
	/**
	 * @return the accounts
	 */
	public List<Account> getaccounts() {
		return accounts;
	}
	/**
	 * @param accounts the accounts to set
	 */
	public void setaccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
	 
	
	

	
	
	
	
}
