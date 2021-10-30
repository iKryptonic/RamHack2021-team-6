package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

// not implemented - instead we will send the json file to the front-end with an AJAX request. 
public class UniversityLookup {
    protected static HashMap<Integer, String> allUniversities = new HashMap<Integer, String>();
	
    // insert university data into a hashmap for constant lookup. (time complexity is spent on initial map population.)
    public UniversityLookup() throws IOException {
	
	
	InputStream universityDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("us_institutions.json");
	
	BufferedReader bR = new BufferedReader(  new InputStreamReader(universityDataStream));
	String line = "";

	StringBuilder responseStrBuilder = new StringBuilder();
	while((line =  bR.readLine()) != null)
	    {
	        responseStrBuilder.append(line);
	    }
	    universityDataStream.close();
	JSONArray result = new JSONArray(responseStrBuilder.toString());     
	long start = System.currentTimeMillis();
	for (int i = 0; i < result.length(); i++)
	{
	    allUniversities.put(i, result.getJSONObject(i).getString("institution"));
	}
    }
    
    public HashMap<Integer, String> getUniversities()
    {
	return allUniversities;
    }
}
