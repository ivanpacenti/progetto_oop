/**
 * 
 */
package it.univpm.progetto.model;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author ivan
 *
 */
public class APICall {
	 
	
	public ArrayList<Accounts> searchapi(String query) {
		
		ArrayList<Accounts> accounts=new ArrayList<Accounts>();
		
		try {
            
			String address=query.replaceAll(" ", "%20");
            URL url = new URL("https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/users/search.json?q="+address);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connection is made
            int responseCode = conn.getResponseCode();

            // if 200 ->  OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                String informationString ="";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString+=scanner.nextLine();
                }
                //Close the scanner
                scanner.close();

                try {
        			Gson gson=new GsonBuilder().setPrettyPrinting().create();
        			JSONArray array=new JSONArray(informationString);
        			for(int i=0;i<array.length();i++)
        			{
        				JSONObject obj=array.getJSONObject(i);
        				accounts.add(new Accounts(
        						obj.getString("id_str"),
        						obj.getString("name"),
        						obj.getString("screen_name"),
        						obj.getInt("followers_count"),
        						obj.getInt("friends_count"),
        						obj.getInt("listed_count"),
        						obj.getInt("statuses_count"),
        						obj.getString("profile_image_url")));
        				
        			}
        			
        		}catch(JSONException e)
        		{
        			e.printStackTrace();
        		}
        		



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return accounts;
		
	}
}
