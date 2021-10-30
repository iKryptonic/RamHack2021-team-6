package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TicketMasterInterface {
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
    
}
