package edu.wssu.ramhack2021.main;
import jakarta.servlet.http.*;
import utils.GooglePlacesInterfaceAPI;
import utils.TicketMasterInterface;
import utils.TicketMasterInterface.Event;
import utils.TicketMasterInterface.SORT_METHOD;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

import com.google.maps.errors.ApiException;

@WebServlet("/HomePage")
public class HomePage extends HttpServlet
{
    
    /**
     * Generated UID
     */
    private static final long serialVersionUID = 8404094855512891084L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException
    {
	String universityName = null;
	
	universityName = req.getParameter("universityName");
	
	if(universityName != null) {
	    req.setAttribute("universityName", universityName);
	    req.setAttribute("showData", "y");
	    String universityZip = null;
	    try {
		universityZip = new GooglePlacesInterfaceAPI().findZipFromUniversityName(universityName);
	    } catch (ApiException | InterruptedException | IOException e) {
		e.printStackTrace();
	    }
	    
	    if(universityZip != null) {
		Event[] allEventsNear = new TicketMasterInterface().getEventList(25, universityZip, "", 60, SORT_METHOD.DATE_ASC);
		req.setAttribute("Events", allEventsNear);
	    }
	    
	} else {
	    req.setAttribute("showData", "n");
	}
        
        RequestDispatcher view = req.getRequestDispatcher("HomePage.jsp");
        view.forward(req, res);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException
    {
	System.out.println("Post recv");
	String universityName = req.getParameter("autocomplete-input");
	System.out.println(universityName);
	
        RequestDispatcher view = req.getRequestDispatcher("HomePage.jsp");
        view.forward(req, res);
    }
}