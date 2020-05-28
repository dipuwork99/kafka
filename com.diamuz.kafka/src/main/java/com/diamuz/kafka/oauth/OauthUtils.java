package com.diamuz.kafka.oauth;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.impl.execchain.MainClientExec;

import java.io.*;
import java.net.URL;
import java.util.Base64;

public class OauthUtils {

	private static final String clientId = "";// clientId
	private static final String callBackUrl = "";// The url defined in WSO2
	private static final String authorizeUrl = "https://api.byu.edu/authorize";
	String authorizationRedirect = getAuthGrantType(callBackUrl);

	
	//login.salesforce.com/services/oauth2/token?grant_type=authorization_code&redirect_uri=https://www.yourappname.com/api/callback&client_id=YOUR_CONSUMER_ID&client_secret=YOUR_CONSUMER_SECRET&code=aWekysIEeqM9PiThEfm0Cnr6MoLIfwWyRJcqOqHdF8f9INokharAS09ia7UNP6RiVScerfhc4w%3D%3D
	private static String getAuthGrantType(String callbackURL) {
		return authorizeUrl + "?response_type=code&client_id=" + clientId + "&redirect_uri=" + callbackURL
				+ "&scope=openid";
	}

//Wait for user to logIn and then
//getAccessToken(with the authorizationCode from header name 'authorization_code', callbackUrl);
//Then call useBearerToken('access_token')
	private static void useBearerToken(String bearerToken) {
		BufferedReader reader = null;
		try {
			URL url = new URL("https://api.byu.edu:443/echo/v1/status?test=testing");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			StringWriter out = new StringWriter(
					connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			String response = out.toString();
			System.out.println(response);
		} catch (Exception e) {

		}
	   
	}
	
	private static void getInitialToken(String bearerToken) {
		BufferedReader reader = null;
		try {
			URL url = new URL("https://login.salesforce.com/services/oauth2/token?grant_type=authorization_code&redirect_uri=https://www.yourappname.com/api/callback&client_id=3MVG9sh10GGnD4DvoFU_47fJhQsV7Uc2pkXy7Ol3zDJVHBpEQLxFgZuCaws8Q_lmvV8jK3zzRDcGghWrOUrCt&client_secret=6AD0520502D12B0FEA3D5C38E5204BC5A9ACE7CB150B1490500EBC84394163ED&code=Bxu5CjZGI4YJWHeTtc5yZbdNa");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			StringWriter out = new StringWriter(
					connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			String response = out.toString();
			System.out.println(response);
		} catch (Exception e) {
            System.out.println(e);
		}
		
	}	
	
	public static void main(String[] args) {
		getInitialToken("");
	}

}

	
	