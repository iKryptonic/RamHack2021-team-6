package edu.wssu.ramhack2021.main;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;
import java.util.Random;

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
	res.setContentType("text/html");
	PrintWriter pw = res.getWriter();
	
	Random numberGen = new Random();
	
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