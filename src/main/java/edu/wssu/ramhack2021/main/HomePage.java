package edu.wssu.ramhack2021.main;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;

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
        RequestDispatcher view = req.getRequestDispatcher("HomePage.jsp");
        view.forward(req, res);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException
    {
        RequestDispatcher view = req.getRequestDispatcher("HomePage.jsp");
        view.forward(req, res);
    }
}