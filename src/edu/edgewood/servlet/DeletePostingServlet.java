/*created by Yanjun
*/
package edu.edgewood.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.edgewood.model.Posting;
import edu.edgewood.svc.MainPageManager;
import edu.edgewood.svc.PostingManager;

/**
 * Servlet implementation class DeletePostingServlet
 */
@WebServlet(name = "delete", urlPatterns = { "/delete" })
public class DeletePostingServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private PostingManager postingManager;
    private MainPageManager mainPageManager;
	
	public DeletePostingServlet() {
		postingManager = new PostingManager();
		mainPageManager = new MainPageManager();
	}
       
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		//get cookie  
	     String cookieName = "userId";
		 
		 boolean cookieExisit = false;
			  
			  //looking for cookie
			  Cookie[] cookies = request.getCookies(); 
			  if (cookies != null) { 
				  for(int i=0; i<cookies.length; i++) 
				  { Cookie cookie = cookies[i]; 
				       if(cookieName.equals(cookie.getName())) 
				         { 
				    	   cookieExisit = true;}//end if (userId found)
				  }//end for 
			  }//end if 
		
		if(cookieExisit) {	
		
		postingManager.remove(id);
		
		List<Posting> postings = mainPageManager.getAll();
		request.setAttribute("postings", postings);
		request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);	
		
		}
		
		else {
			
			try (PrintWriter writer = response.getWriter()) {
				
				writer.println("<!DOCTYPE html>");
				writer.println("<html>");
				writer.println("<head>");
				writer.println("<meta charset='UTF-8'>");
				writer.println("<title>Delete Error</title>");
				writer.println("<link rel='stylesheet 'href='css/common.css'/>");
				writer.println("<Script src='js/ext/jquery-3.3.1.min.js'></Script>");
				writer.println("<Script src='js/common.js'></Script>");
				writer.println("</head>");
				writer.println("<body>	");
				writer.println("<div id='container'>");
				writer.println("<div id='header'>");
				writer.println("<h1>UACS-417-ADVENCED WEB DEVELOPMENT-SPRING-2019</h1>");
				writer.println("<div id='menu'>");
				writer.println("<a id='loginLnkId' href=''>Login</a>");
				writer.println("<a id='RegisterLnkId' href=''>Register</a>");
				writer.println("</div>");				
				writer.println("</div>");
				writer.println("<div id='content'>");
				writer.println("<p>Please log in first to delete posting.</p>");
				writer.println("</div>");				
				writer.println("</div>");
				writer.println("</body>");
				writer.println("</html");		
			} catch (Exception ex) {
				ex.printStackTrace();
			}	
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
