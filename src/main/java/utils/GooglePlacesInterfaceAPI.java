package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.maps.FindPlaceFromTextRequest;
import com.google.maps.FindPlaceFromTextRequest.InputType;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.PlacesSearchResult;

public class GooglePlacesInterfaceAPI {
    
    protected final Properties prop = new Properties();
    
    {
	String propFile = "config.properties";
	ClassLoader loader = Thread.currentThread().getContextClassLoader();            
	InputStream is = null;
	
	try {
	    is = loader.getResourceAsStream(propFile);
	    prop.load(is);
	} catch (NullPointerException | IOException unhandledError) {
	    unhandledError.printStackTrace(System.out);
	}
    }
    
    protected PlacesSearchResult doPlaceSearch(GeoApiContext context, String universityName) throws ApiException, InterruptedException, IOException {
	// execute google place search query.
	FindPlaceFromText placeSearch = PlacesApi.findPlaceFromText(context, universityName, InputType.TEXT_QUERY).fields(
		FindPlaceFromTextRequest.FieldMask.FORMATTED_ADDRESS
		).await();
	
	return placeSearch.candidates.length > 0 ? placeSearch.candidates[0] : null;
    }
    
    public String findZipFromUniversityName(String universityName) throws ApiException, InterruptedException, IOException {
	String retrievedKey = null;
	
	if(prop.containsKey("googleApiKey"))
	    retrievedKey = prop.getProperty("googleApiKey");
	
	// null check for key, return nothing if no key exists.
	if(retrievedKey != null) 
	{
	    
	// create the singleton google told us not to instantiate every time we're making a request :)
    	GeoApiContext context = new GeoApiContext.Builder().apiKey(retrievedKey).build();
    	// grab google place search result
    	PlacesSearchResult searchResult = doPlaceSearch(context, universityName);
    
    	// parse out zip codes from address string matching by 5 digits
            Pattern p = Pattern.compile("\\d{5}");
            Matcher m = p.matcher(searchResult.formattedAddress);
            
            // gather possible zip codes ( some addresses may have 5 digits, zip code will always be at the end of the string.)
            String[] possibleZips = new String[2];
            int pointer = -1;
            
            // append possible zips to array
            while(m.find() && pointer < 2)
            {
                pointer++;
                possibleZips[pointer] = m.group();
            }
    	
            // return most probable zip code
            return possibleZips[pointer];
            }
	// no key was detected
	throw new IOException("key 'googleApiKey' was not found in config.properties. (Did you set-up the project properly?)");
	}
    }
