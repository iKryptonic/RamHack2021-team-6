package edu.wssu.ramhack2021.main;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;

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
	res.setContentType("text/html");
	PrintWriter pw = res.getWriter();
	
	pw.println("<html><body>");
	pw.println("Hello World!");
	pw.println("</body></html>");
	
	pw.close();
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException
    {
	
    }
}