/*created by Yanjun*/

package edu.edgewood.servlet;

import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.edgewood.model.Posting;
import edu.edgewood.model.User;
import edu.edgewood.svc.MainPageManager;
import edu.edgewood.svc.PostingManager;

/**
 * Servlet implementation class AddPostingServlet
 */
@WebServlet(name = "add", urlPatterns = { "/add" })

public class AddPostingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PostingManager postingManager;
	private MainPageManager mainPageManager;
	
	public AddPostingServlet() {
		postingManager = new PostingManager();
		mainPageManager = new MainPageManager();
	}
           
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/add.jsp")
		.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("Id");		
		String title = request.getParameter("title");
		String shortDescription = request.getParameter("shortDescription");
		String longDescription = request.getParameter("longDescription");
		LocalDateTime ModifiedTime = LocalDateTime.parse (request.getParameter("dateTime"));		
		
		String userId = request.getParameter("user");
		User createdBy = postingManager.getUser(userId);
		User lastModifiedBy = postingManager.getUser(userId);
        
		Posting posting = new Posting();
		
		posting.setId(id);
		posting.setTitle(title);
		posting.setShortDescription(shortDescription);
		posting.setLongDescription(longDescription);
		posting.setCreatedBy(createdBy);
		
		posting.setLastModified(ModifiedTime);
		posting.setLastModifiedBy(lastModifiedBy);	
		
		postingManager.add(posting);
		
		List<Posting> postings = mainPageManager.getAll();
		request.setAttribute("postings", postings);
		request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);
	}

}
