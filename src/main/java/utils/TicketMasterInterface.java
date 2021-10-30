package utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;



public class TicketMasterInterface {
    
    public class Event {
	private String id, name, description, url, date, genre, ageRestriction;
	private String[]  images;
	
	public Event(){
	    
	}
	
	protected Event(String eventID, String eventName, String eventDescription, String eventURL, 
		String eventDate, String eventGenre, String[] eventImages, String ageRestriction)
	{
	    this.id = eventID;
	    this.name = eventName;
	    this.description = eventDescription;
	    this.url = eventURL;
	    if(eventDate != "") 
	    {
		String tempDate = eventDate;
		final String NEW_FORMAT = "MM/dd/yyyy hh:mm";
		
		SimpleDateFormat formatter = new SimpleDateFormat(NEW_FORMAT);
		Date newDate = null;
		
		newDate = Date.from(Instant.parse(eventDate));
		
		this.date = formatter.format(newDate);
	    } else {
		this.date = "TBA";
	    }
	    this.genre = eventGenre;
	    this.images = eventImages;
	    this.ageRestriction = ageRestriction;
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
	    public String[] getImages()
	    {
		return this.images;
	    }
	    public String getAgeRestriction()
	    {
		return this.ageRestriction;
	    }
	    
	    public HashMap<String, String> getEventDataFormatted()
	    {
		HashMap<String, String> eventDataFormatted = new HashMap<String, String>();
		eventDataFormatted.put("eventID", this.id);
		eventDataFormatted.put("eventName", this.name);
		eventDataFormatted.put("eventDescription", this.description);
		eventDataFormatted.put("eventDate", this.date);
		eventDataFormatted.put("eventGenre", this.genre);
		eventDataFormatted.put("eventAgeRestrict", this.ageRestriction);
		eventDataFormatted.put("eventPage", this.url);
		
		return eventDataFormatted;
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
    
    protected Event parseEventData(JSONObject rec) {
	String id = ""; String name = "";  String description = ""; 
        String url = ""; String date = ""; String genre = ""; 
        String ageRestriction = ""; 
        
        String[] images;
    	
        if(rec.has("id"))
        {
    	    id = rec.getString("id");
        } else {
            id = "N/A"; // this should honestly throw an exception because why don't you have an ID?
    	}
    	if(rec.has("name"))
    	{
    	    name = rec.getString("name");
    	} else {
    	    name = "N/A";
    	}
    	if(rec.has("pleaseNote"))
    	{
    	    description = rec.getString("pleaseNote");
    	} else {
    	    description = "N/A";
    	}
    	if(rec.has("url"))
    	{
    	    url = rec.getString("url");
    	} else {
    	    url = "N/A";
    	}
    	if(rec.has("dates"))
    	{
    	    JSONObject dates = rec.getJSONObject("dates").getJSONObject("start");
    	    if(dates.has("dateTime")) 
    	    {
    		date = dates.getString("dateTime");
    	    }
    	} else {
    	    date = "N/A";
    	}
    	
    	if(rec.has("classifications"))
    	{
    	    genre = rec.getJSONArray("classifications").getJSONObject(0).getJSONObject("genre").getString("name");
    	} else {
    	    genre = "N/A";
    	}
    	
    	if(rec.has("ageRestrictions"))
    	{
    	    // lambda for finding age restrictions
    	    ageRestriction = rec.getJSONObject("ageRestrictions").toString().toLowerCase().equals("false") ? "No" : "Yes";
    	} else {
    	    ageRestriction = "N/A";
    	}
    	
    	if(rec.has("images"))
    	{
    	    JSONArray tempImages = rec.getJSONArray("images");
    	    // initialize images table with a string
    	    images = new String[tempImages.length()];
    	    int ptr = 0;
    	    
    	    for(int j = 0; j < tempImages.length(); j++) {
		JSONObject imageRecord = tempImages.getJSONObject(j);
    		if(imageRecord.has("url") && imageRecord.has("width")) {
    		    int imageWidth = imageRecord.getInt("width");
    		    if(imageWidth >= 300) { // ignore images smaller than 300px
    			images[ptr] = imageRecord.getString("url"); 
    			ptr++;
    		    }
    		}
    	    }
    	} else {
    	    images = new String[0];
    	}
    	
	return new Event(id, name, description, url, date, genre, images, ageRestriction);
    }
    
    protected final String baseUrl = "https://app.ticketmaster.com/discovery/v2/";
    
    public Event getEventByID(String eventID) {

	String response = null;
	String apiKey = prop.getProperty("ticketMasterApiKey");
	
	// format our API key into the request
	String finalRequest = String.format(baseUrl + "events.json?id=%s&apikey=%s", eventID, apiKey);
	QuickHTTPRequest webAPI = new QuickHTTPRequest();
	
	// send request to ticketmaster
	response = webAPI.makeRequest(finalRequest);
	
	// null check
	if(response != null)
	{
	    JSONObject arrayData = new JSONObject(response);
	    JSONArray eventData = null;
	    if(arrayData.has("_embedded")) 
	    {
		eventData = arrayData.getJSONObject("_embedded").getJSONArray("events");
	    } else {
		return null;
	    }
	    
	    return parseEventData(eventData.getJSONObject(0));
	}
	return null;
    }
    
    public Event[] getEventList(int size, String postalCode, String keyword, int radius, SORT_METHOD sortMethod)
    {
	String response = null;
	String apiKey = prop.getProperty("ticketMasterApiKey");
	
	// format our API key into the request
	String finalRequest = String.format(baseUrl + "events.json?size=%d&apikey=%s&postalCode=%s&keyword=%s&radius=%d&sort=%s", size, apiKey, postalCode, keyword, radius, sortMethod);
	
	QuickHTTPRequest webAPI = new QuickHTTPRequest();
	
	// send request to ticketmaster
	response = webAPI.makeRequest(finalRequest);
	
	if(response != null)
	{
	    JSONObject arrayData = new JSONObject(response);
	    JSONArray eventData = null;
	    if(arrayData.has("_embedded")) 
	    {
	    eventData = arrayData.getJSONObject("_embedded").getJSONArray("events");
	    
	    Event[] events = new Event[size]; // create new array to hold formatted events
	    for (int i = 0; i < eventData.length(); ++i) {
		JSONObject rec = eventData.getJSONObject(i);
		
		events[i] = parseEventData(rec);
		}
	    return events;
	    }
	}
	return null;
    }  
}
