<!-- created by Yanjun -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="edu.edgewood.model.*"    %>   
<%@ page import="edu.edgewood.dao.*"    %>  
<%@ page import="edu.edgewood.svc.*"    %>  
<%@ page import="java.time.LocalDateTime" %>
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
		<title>Update</title>
		<link rel="stylesheet" href="css/common.css"/>
		<Script src='js/ext/jquery-3.3.1.min.js'></Script>
		<Script src='js/update.js'></Script>
	</head>
	<body>	
	
		<div id='container'>	
		
			<%
				if (cookieExisit){                         
					MainPageDao dao = new MainPageDao();
					User loginUser = dao.getUserById(userId);  
					String userName = loginUser.getFirstName() + " " + loginUser.getLastName();		
			%>	
				<div id='header'>
				<h1>UACS-417-ADVENCED WEB DEVELOPMENT-SPRING-2019</h1>
				<div id='menu'>
				<span><%=userName%></span>
	            <a id='logoutLnkId'>Logout</a>  
				</div>		
				</div>				
			
				<div id = 'content'>
				<h2>Update Posting: </h2>
				<form id ='updatePostingFormId' action='update' method='post'>
				<input type='hidden' name='id' value='${requestScope.posting.id}'>
				<input type='hidden' id='modifiedTimeId' name='lastModified' value='<%=LocalDateTime.now()%>'/>
				<input type='hidden' id='modifiedUserId' name='userId' value='<%=userId%>'/>
				    <b>Title:</b>
					<input type='text' id='titleId' name='title' value='${requestScope.posting.title}'/> <span class='error'>*</span>				
					<p/>
					<b>Short Comment:</b><span class='error'>*</span><br/>
					<textarea id='shortDescriptionId' cols="20" rows="3" name='shortDescription'>${requestScope.posting.shortDescription}</textarea>
				 	<p/>
				 	<b>Detail Comment:</b><br/>	
				 	<textarea id='longDescriptionId' cols="40" rows="10" name='longDescription'>${requestScope.posting.longDescription}</textarea>		 	
				 	<p/>
					<input type='button' id='updateBtnId' value='Update'/>
					<input type='button' id='cancelBtnId' value='Cancel'/>
					<br>
					<span class='error'>* required</span>		
				</form>			
				</div>
			
			<% }
				else		
			{%>	
				<div id='header'>
				<h1>UACS-417-ADVENCED WEB DEVELOPMENT-SPRING-2019</h1>
	            <div id='menu'>
	        	<a id='loginLnkId' href=''>Login</a>						
				<a id='RegisterLnkId' href=''>Register</a>					
				</div>
				</div>
				<div id="content">
				<p class="error">Log in first to edit posting.</p>
				</div>
				
				<%} %>
				
			<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
		
		
	</div>
	</body>
</html>