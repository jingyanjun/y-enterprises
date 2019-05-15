<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="edu.edgewood.model.*"    %>   
<%@ page import="edu.edgewood.dao.*"    %>  
<%@ page import="edu.edgewood.svc.*"    %>  
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.*" %> 

<%	
    //get cookie  
     String cookieName = "userId";
	 String userId = "";
	 boolean cookieExisit = false;
		  
		  //looking for cookie
		  Cookie[] cookies = request.getCookies(); 
		  if (cookies != null) { 
			  for(int i=0; i<cookies.length; i++) 
			  { Cookie cookie = cookies[i]; 
			       if(cookieName.equals(cookie.getName())) 
			         { userId = cookie.getValue();
			    	   cookieExisit = true;}//end if (userId found)
			  }//end for 
		  }//end if 
%>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
<link rel="stylesheet" href="css/common.css"/>
<script src='js/ext/jquery-3.3.1.min.js'></script>
<script src='js/common.js'></script>
</head>


<body>

	<div id='container'>
	
				<div id='header'>
				<h1>UACS-417-ADVENCED WEB DEVELOPMENT-SPRING-2019</h1>
				</div>
	
			<%
				if (cookieExisit){
					MainPageDao dao = new MainPageDao();
					User loginUser = dao.getUserById(userId);
					String userName = loginUser.getFirstName() + " " + loginUser.getLastName();		
			%>	
				
					<div id='menu'>
					<span><%=userName%></span>
		            <a class = 'menu' id='logoutLnkId' href=''>Logout</a>  
					</div>		
				
				
			 <% }
				else		
			 {%>	
				
		            <div id='menu'>
		        	<a class = 'menu' id='loginLnkId' href=''>Login</a>						
					<a class = 'menu' id='RegisterLnkId' href=''>Register</a>					
					</div>
				
				
				<%} %>
				
				
	
				<div id="content">
		
						<%
						    List<Posting> postings = (List)request.getAttribute("postings");
							if(postings == null || postings.isEmpty()){
						%>
								
								Nothing Found 
							
						<%	
							}else{
						%>
						
						<% if (cookieExisit){ %>
						<a class='newButton' href='add'>Add New Post</a>
						<p></p>
						<%} %>
								
							
								
						<%
								for(Posting posting : postings){
						%>
						
						<table>
							<tr>
								<td><b><%=posting.getCreatedDate()%></b>   <%=posting.getTitle() %></td>									
							</tr>
							
							<tr>
								<td><%=posting.getShortDescription() %>
								<a href='details?id=<%=posting.getId()%>'>Read more...</a>
								</td>
							</tr>
							
							<% if (cookieExisit){ %>
							<tr>
								<td>
								<a class = 'Button' href='update?id=<%=posting.getId()%>'>Edit</a>
								
	    		 			    <a class='confirmation' href='delete?id=<%=posting.getId()%>' class='confirmation'>Delete</a>
	    		 			    
	    		 			    
								</td>
							</tr>
							<%} %>											
														
							</table>
							
							<p></p>
						<%			
							}
						%>						
						
						<%
						}
						%>
				</div>
				
				<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
			</div>
</body>
</html>