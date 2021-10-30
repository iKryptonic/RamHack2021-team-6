package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;



public class TicketMasterInterface {
    
    public class Event {
	private String id, name, description, url, date, genre;
	    Event(String eventID, String eventName, String eventDescription, String eventURL, 
		    String eventDate, String eventGenre)
	    {
		this.id = eventID;
		this.name = eventName;
		this.description = eventDescription;
		this.url = eventURL;
		this.date = eventDate;
		this.genre = eventGenre;
	    }
	    
	    public String getID()
	    {
		return this.id;
	    }
	    public String getName()
	    {
		return this.name;
	    }
	    public String getDescription()
	    {
		return this.description;
	    }
	    public String getURL()
	    {
		return this.url;
	    }
	    public String getDate()
	    {
		return this.date;
	    }
	    public String getGenre()
	    {
		return this.genre;
	    }
    }
    
    // non-public constructor exposure.
    public TicketMasterInterface() {}
    
    public enum SORT_METHOD {
	DEFAULT ("random"),
	NAME_ASC ("name,asc"),
	NAME_DESC ("name,desc"),
	DATE_ASC ("date,asc"),
	DATE_DESC ("date,desc"),
        RELEVANCE_ASC ("relevance,asc"),
        RELEVANCE_DESC ("relevance,desc"),
        DISTANCE_ASC ("distance,asc"),
        DISTANCE_DESC ("distance,desc"),
        NAME_DATE_ASC ("name,date,asc"),
        NAME_DATE_DESC ("name,date,desc"),
        DATE_NAME_ASC ("date,name,asc"),
        DATE_NAME_DESC ("date,name,desc"),
        DISTANCE_DATE_ASC ("distance,date,asc"),
        DISTANCE_DATE_DESC ("distance,date,desc");
	
	private String currentMethod = null;
	private String literalString = null;
	
	SORT_METHOD(String method){
	    this.currentMethod = method;
	    
	    switch (method) 
	    {
    	    case "random":
    		this.literalString = "Random";
    		break;
    	    case "name,asc":
    		this.literalString = "Name Ascending";
    		break;
    	    case "name,desc":
    		this.literalString = "Name Descending";
    		break;
    	    case "date,asc":
    		this.literalString = "Date Ascending";
    		break;
    	    case "date,desc":
    		this.literalString = "Date Descending";
    		break;
    	    case "relevance,asc":
    		this.literalString = "Relevance Ascending";
    		break;
    	    case "relevance,desc":
    		this.literalString = "Relevance Descending";
    		break;
    	    case "distance,asc":
    		this.literalString = "Distance Ascending";
    		break;
    	    case "distance,desc":
    		this.literalString = "Distance Descending";
    		break;
    	    case "name,date,asc":
    		this.literalString = "Name, Date Ascending";
    		break;
    	    case "name,date,desc":
    		this.literalString = "Name, Date Descending";
    		break;
    	    case "date,name,asc":
    		this.literalString = "Date, Name Ascending";
    		break;
    	    case "date,name,desc":
    		this.literalString = "Date, Name Descending";
    		break;
    	    case "distance,date,asc":
    		this.literalString = "Distance, Date Ascending";
    		break;
    	    case "distance,date,desc":
    		this.literalString = "Distance, Date Descending";
    		break;
	    }
	}
	
	public String toLiteral() { return this.literalString; }
	
	@Override public String toString() { return this.currentMethod; }
    }
    
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
    
    protected final String baseUrl = "https://app.ticketmaster.com/discovery/v2/";
    
    public Event[] getEventList(int size, String postalCode, String keyword, int radius, SORT_METHOD sortMethod)
    {
	String response = null;
	String apiKey = prop.getProperty("ticketMasterApiKey");
	
	// format our API key into the request
	String finalRequest = String.format(baseUrl + "events.json?size=%d&apikey=%s&postalCode=%s&keyword=%s&radius=%d&sort=%s", size, apiKey, postalCode, keyword, radius, sortMethod);
	QuickHTTPRequest webAPI = new QuickHTTPRequest();
	
	// send request to ticketmaster
	response = webAPI.makeRequest(finalRequest);
	
	// null check
	if(response != null)
	{
	    JSONArray eventData = new JSONObject(response).getJSONObject("_embedded").getJSONArray("events");
	    
	    Event[] events = new Event[size]; // create new array to hold formatted events
	    for (int i = 0; i < eventData.length(); ++i) {
		JSONObject rec = eventData.getJSONObject(i);
		
		String id = ""; String name = "";  String description = ""; 
		String url = ""; String date = ""; String genre = "";
		
		if(rec.has("id"))
		    id = rec.getString("id");
		if(rec.has("name"))
		    name = rec.getString("name");
		if(rec.has("pleaseNote"))
		    description = rec.getString("pleaseNote");
		if(rec.has("url"))
		    url = rec.getString("url");
		if(rec.has("dates"))
		{
		    JSONObject dates = rec.getJSONObject("dates").getJSONObject("start");
		    if(dates.has("dateTime")) 
		    {
			date = dates.getString("dateTime");
			}
		    }
		if(rec.has("classifications"))
		    genre = rec.getJSONArray("classifications").getJSONObject(0).getJSONObject("genre").getString("name");
		
		events[i] = new Event(id, name, description, url, date, genre);
		}
	    return events;
	}
	return null;
    }  
}
