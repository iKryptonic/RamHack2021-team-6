package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuickHTTPRequest
{
    public String makeRequest(String url) 
    {
	URL urlObject;
	HttpURLConnection httpConnection = null; 
	StringBuffer returnedHTTPResponse = null;
	
	try 
	{	
	    urlObject = new URL(url);
	    
	    // Create new connection and cast it to HttpURLConnection for method inheritance
	    httpConnection = (HttpURLConnection) urlObject.openConnection();
	    httpConnection.setRequestMethod("GET");
	    
	    // Create connection as a Firefox User-Agent.
	    httpConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
	    
	    if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) 
	    { // success
		BufferedReader in = null;
		in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
		String inputLine;
		returnedHTTPResponse = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) 
		{
		    returnedHTTPResponse.append(inputLine);
		    }
		
		in.close(); // finish reading response.
		
		return returnedHTTPResponse.toString();
		}
	    }
	catch (IOException e1) 
    	{
    	    e1.printStackTrace(System.out);
    	}
	
	return null; // no connection was able to be made.
    	}
    }