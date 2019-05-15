/*created by Yanjun
*/
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
		postingManager.remove(id);
		
		List<Posting> postings = mainPageManager.getAll();
		request.setAttribute("postings", postings);
		request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(request, response);		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
