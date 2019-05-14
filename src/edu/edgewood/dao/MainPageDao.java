package edu.edgewood.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.edgewood.model.Posting;
import edu.edgewood.model.User;

public class MainPageDao extends AbstractJdbcDao{

	public List<Posting> getAll() throws Exception{
		String sql = "select * from posting order by created_date desc";	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Posting> postings = new ArrayList<>();
			
			while(rs.next()) {
				Posting posting = createPosting(rs);
				postings.add(posting);
			}
			return postings;
		}finally {
			releaseResources(conn, stmt, rs);
		}
	}
	
	public User getUserById(String id) throws Exception {
		String sql = "select * from user where id = ?";	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			
			if(rs.next()) {
				return createUser(rs);
			}
			return null;
			
			
			
		}finally {
			releaseResources(conn, stmt, rs);
		}
		
	}
	
	public User getUserByLogin(String userName, String password) throws Exception{
		String sql = "select * from user where id= ? and password = ?";	 //edit by yanjun: "change user_name" to "id"
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			
			if(rs.next()) {
				return createUser(rs);
			}
			return null;
			
			
			
		}finally {
			releaseResources(conn, stmt, rs);
		}
	}
	
	private Posting createPosting(ResultSet rs) throws Exception{
		String id = rs.getString("id");
		LocalDate createdDate = rs.getDate("created_date").toLocalDate();  
		User createdBy = this.getUserById(rs.getString("created_by"));
		String title = rs.getString("title");
		String shortDescription = rs.getString("short_description");
		String longDescription = rs.getString("long_description");
		
		LocalDateTime lastModified = null;
		if(rs.getTimestamp("last_modified") != null) {
			lastModified = rs.getTimestamp("last_modified").toLocalDateTime();
		};
		
		User lastModifiedBy = null;
		if(rs.getString("last_modified_by") != null) {
			lastModifiedBy = this.getUserById(rs.getString("last_modified_by"));
		};
		
		
		Posting posting = new Posting();
		posting.setId(id);
		posting.setCreatedDate(createdDate);
		posting.setCreatedBy(createdBy);
		posting.setTitle(title);
		posting.setShortDescription(shortDescription);
		posting.setLongDescription(longDescription);
		
			posting.setLastModified(lastModified);	
		
			posting.setLastModifiedBy(lastModifiedBy);
			
		return posting;
	}
	
	private User createUser(ResultSet rs) throws Exception{
		String id = rs.getString("id");
		String password = rs.getString("password");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		LocalDate createdDate = rs.getDate("create_date").toLocalDate();
		
		
		User user = new User();
		
		user.setUserId(id);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setCreatedDate(createdDate);
		
		return user;
	}

/**
 * Created by Yanjun: update(Posting)
 */
public boolean update (Posting posting) throws Exception{
	
	String sql = "update movie set title = ?, "
			+ "short_description = ?, "
			+ "long_description = ? "
			+ "last_modified = ? "
			+ "last_modified_by = ? "
			+ "where id = ? ";
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	try {
		conn = getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, posting.getTitle());
		stmt.setString(2, posting.getShortDescription());
		stmt.setString(3, posting.getLongDescription());		
		stmt.setTimestamp(4, Timestamp.valueOf(posting.getLastModified()));		
		stmt.setString(5, posting.getLastModifiedBy().getUserId());
		
		stmt.execute();
		return true;
		
	}finally {
		releaseResources(conn,stmt,null);
	}
			
}

/**
 * Created by Yanjun: get(posting_id)
 */
public Posting get(String postingId) throws Exception{
	String sql = "select * from posting where id = ?";
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	try {
		conn = getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1,postingId);
		rs = stmt.executeQuery();		
		
		if(rs.next())
			
			return createPosting(rs);
		
		else return null;
		
		
		
	}finally {
		releaseResources(conn,stmt,rs);
	}
	
}

/**
 * Created by Yanjun: insert(Posting)
 */
public void insert(Posting posting) throws Exception{
	String sql = "insert into posting(id,created_date,created_by,title,short_description,long_description,last_modified,last_modified_by)"
				+ " value(?,?,?,?,?,?,?,?) ";

Connection conn = null;
PreparedStatement stmt = null;

try {
	conn = getConnection();
	stmt = conn.prepareStatement(sql);
	stmt.setString(1, posting.getId());
	stmt.setDate(2, Date.valueOf(posting.getCreatedDate()));
	String userId = posting.getCreatedBy().getUserId();
	stmt.setString(3, userId);
	stmt.setString(4, posting.getTitle());
	stmt.setString(5, posting.getShortDescription());
	stmt.setString(6, posting.getLongDescription());
	stmt.setTimestamp(7, Timestamp.valueOf(posting.getLastModified()));
	stmt.setString(8, posting.getLastModifiedBy().getUserId());
	
	stmt.execute();
	
}finally {
	releaseResources(conn,stmt,null);
}

}

/**
 * Created by Yanjun: delete(Posting)
 */
public boolean delete (String id) throws Exception{
	String sql = "delete from posting where id = ?";
	Connection conn = null;
	PreparedStatement stmt = null;
	try {
		conn = getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.execute();
		return true;
	}finally {
		releaseResources(conn,stmt,null);
	}
			
}

}


