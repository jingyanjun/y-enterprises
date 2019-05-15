<!-- created by Yanjun -->

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

<!-- check if cookie exist -->
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
		<title>Add Posting</title>
		<link rel="stylesheet" href="css/common.css"/>
		<Script src='js/ext/jquery-3.3.1.min.js'></Script>
		<Script src='js/common.js'></Script>
		<Script src='js/add.js'></Script>
	</head>
	
	<body>	
		<div id = 'container'>
		
			<%
				if (cookieExisit){
					MainPageDao dao = new MainPageDao();
					User loginUser = dao.getUserById(userId);
					String userName = loginUser.getFirstName() + " " + loginUser.getLastName();		
			%>	
				<div id='header'>
				<h1>UACS-417-ADVENCED WEB DEVELOPMENT-SPRING-2019</h1>
				</div>
				<div id='menu'>
				<span><%=userName%></span>
	            <a class = 'menu' id='logoutLnkId'>Logout</a>  
				</div>		
				
				<div id = 'content'>
				<h2>Add New Posting: </h2>
				<form id ='newPostingFormId' action='add' method='post'>
				
				<input type='hidden' id='postingId' name='Id' value='<%=UUID.randomUUID().toString()%>'/>
				<input type='hidden' id='DateTimeId' name='dateTime' value='<%=LocalDateTime.now()%>'/>
				<input type='hidden' id='UserId' name='user' value='<%=userId %>'/>
				
				    <b>Title:</b>
					<input type='text' id='titleId' name='title' value=''/><span class='error'>*</span>
					
					<p/>
					<b>Short Comment:</b> <span class='error'>*</span><br/> <br/>
					<textarea id='shortDescriptionId' cols="20" rows="3" name='shortDescription'></textarea>
				 	<p/>
				 	<b>Detail Comment:</b><br/>	
				 	<textarea id='longDescriptionId' cols="40" rows="10" name='longDescription'></textarea>		 	
				 	<p/>
					<input type='button' id='saveBtnId' value='Save'/>
					<input type='button' id='cancelBtnId' value='Cancel'/>	
					<br/>
					<span class='error'>* required</span>	
				</form>			
				</div>
				
			<% }
				else		
			{%>	
				<div id='header'>
				<h1>UACS-417-ADVENCED WEB DEVELOPMENT-SPRING-2019</h1>
				</div>
	            <div id='menu'>
	        	<a class = 'menu' id='loginLnkId' href=''>Login</a>						
				<a class = 'menu' id='RegisterLnkId' href=''>Register</a>					
				</div>
				
				<div id="content">
				<p>Log in first to add a new posting.</p>
				</div>
				
			<%} %>
			
			
			<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
		
		</div>
	
	</body>
</html>