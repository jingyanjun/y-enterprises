/*created by Yanjun*/

package edu.edgewood.svc;

import java.util.Collections;
import java.util.List;

import edu.edgewood.dao.MainPageDao;
import edu.edgewood.model.Posting;
import edu.edgewood.model.User;

public class PostingManager {
	private MainPageDao dao;
	
	public PostingManager() {
		dao = new MainPageDao();
	}
	
	public User getUser(String userId) {
		try {
			return dao.getUserById(userId);
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}			
	}
	
	public boolean update(Posting posting) {
		try {
			dao.update(posting);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public Posting get(String id) {
		try {
			return dao.get(id);
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean add(Posting posting) {
		try {
			dao.insert(posting);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;		
			}
	}
		
	
	public List<Posting> getAll() {
		try {
			return dao.getAll();
		}catch (Exception ex) {
			ex.printStackTrace();
			return Collections.emptyList();
		}		
			
	}

	public boolean remove(String id) {
		try {
			return dao.delete(id);
		}catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
}
	
	

