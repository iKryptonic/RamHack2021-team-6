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
        RequestDispatcher view = req.getRequestDispatcher("EventDetails.jsp");
        view.forward(req, res);
    }
}