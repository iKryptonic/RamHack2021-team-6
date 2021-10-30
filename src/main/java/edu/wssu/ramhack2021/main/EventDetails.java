package edu.wssu.ramhack2021.main;

import utils.TicketMasterInterface;
import utils.TicketMasterInterface.Event;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.util.Map;

@WebServlet("/EventDetails")
public class EventDetails extends HttpServlet
{
    /**
     * Generated UID
     */
    private static final long serialVersionUID = -6207723279432472406L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException
    {
	// eventName, eventDescription, eventDate, eventGenre, 
	// eventOrganizers, eventAgeRestrict, eventPage, eventImages
	
	
	// cast eventID to string so it's usable
	String eventID = (String) req.getParameter("eventID");
	Event e = new TicketMasterInterface().getEventByID(eventID);
        RequestDispatcher view = null;
	
	if(e != null)
	{
	    for(Map.Entry<String, String> set : e.getEventDataFormatted().entrySet()) 
	    {
		req.setAttribute(set.getKey(), set.getValue());
	    }
	    // Set request attributes for JSP to format into our web page
	    req.setAttribute("eventImages", e.getImages()); // event.getImages()
	    view = req.getRequestDispatcher("EventDetails.jsp");
	} else {
	    view = req.getRequestDispatcher("HomePage.jsp");
	}
	view.forward(req, res);
    }
}