/**
 * 
 */
package it.univpm.progetto.deprecated;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.progetto.model.Accounts;
import it.univpm.progetto.model.Tweets;

/**
 * @author ivan
 *
 */
public abstract  class GetData  {
	APICall apicall = new APICall();

	public List<Accounts> getAccounts(String query) {

		List<Accounts> accounts = new ArrayList<Accounts>();
		
		try {
			String address = query.replaceAll(" ", "%20");
			URL url = new URL("https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/users/search.json?q="
					+ address);
			ObjectMapper mapper=new ObjectMapper();
			List<Accounts> myObjects = 
					Arrays.asList(mapper.readValue(apicall.array(url), 
							Accounts[].class));
			return myObjects;
			/*JSONArray array = apicall.array(url);
			for (int i = 0; i < array.length(); i++) 
			{
				JSONObject obj = array.getJSONObject(i);
				accounts.add(
					new Accounts(obj.getString("id_str"), 
					obj.getString("name"),
					obj.getString("screen_name"),
					obj.getInt("followers_count"),
					obj.getInt("friends_count"),
					obj.getInt("listed_count"),
					obj.getInt("statuses_count"),
					obj.getString("profile_image_url")));
			}*/

		} catch (JacksonException | MalformedURLException e) {
			e.printStackTrace();
			return null;
		}

		

	}
	
	public ArrayList<Tweets> getTweets(String id) {
		
		ArrayList<Tweets> tweets = new ArrayList<Tweets>();
		
		try {

			URL url = new URL(
					"https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/statuses/user_timeline.json?id="
							+ id);
		
				/*	JSONArray array = apicall.array(url);

					for (int i = 0; i < array.length(); i++) {
						JSONObject obj = array.getJSONObject(i);
						JSONObject entities = obj.getJSONObject("entities");
						
						// JSONArray hashtags = entities.getJSONArray("hashtags");
						tweets.add(	new Tweets(obj.getString("created_at"), 
										obj.getString("id_str"), 
										obj.getString("text"),
										entities, 
										obj.getInt("retweet_count"), 
										obj.getInt("favorite_count")));

					}*/
		} catch (JSONException | MalformedURLException e) {
			e.printStackTrace();
		}

		return tweets;

	}
	
}
