/*created by yanjun*/

package edu.edgewood.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.edgewood.svc.MainPageManager;
import edu.edgewood.svc.PostingManager;
import edu.edgewood.model.Posting;
import edu.edgewood.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servlet implementation class updateEntryServlet
 */
@WebServlet(name = "update", urlPatterns = { "/update" })
public class UpdateEntryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PostingManager postingManager;
	private MainPageManager mainPageManager;
	
	public UpdateEntryServlet() {
		postingManager = new PostingManager();
		mainPageManager = new MainPageManager();
	}
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Posting posting = postingManager.get(id);
		request.setAttribute("posting", posting);
		request.getRequestDispatcher("WEB-INF/jsp/update.jsp")
				.forward(request,response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String shortDescription = request.getParameter("shortDescription");
		String longDescription = request.getParameter("longDescription");
		LocalDateTime lastModified = LocalDateTime.parse (request.getParameter("lastModified"));
		String userId = request.getParameter("userId");
		User lastModifiedBy = postingManager.getUser(userId);
        
		Posting posting = new Posting();		
		posting.setId(id);
		posting.setTitle(title);
		posting.setShortDescription(shortDescription);
		posting.setLongDescription(longDescription);
		posting.setLastModified(lastModified);
		posting.setLastModifiedBy(lastModifiedBy);		
	
		postingManager.update(posting);
		
		List<Posting> postings = mainPageManager.getAll();
		request.setAttribute("postings", postings);
		request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);
	
	}

}
