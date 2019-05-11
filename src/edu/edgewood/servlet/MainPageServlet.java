package edu.edgewood.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.edgewood.model.Posting;
import edu.edgewood.svc.MainPageManager;


/**
 * Servlet implementation class MainPageServlet
 */
@WebServlet(name = "main", urlPatterns = { "/main" })
public class MainPageServlet extends HttpServlet {
	
private MainPageManager mainPageManager;
	
	public MainPageServlet() {
		mainPageManager = new MainPageManager();
	}
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Posting> postings = mainPageManager.getAll();
		request.setAttribute("postings", postings);
		request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
