<!-- created by Yanjun -->

<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="edu.edgewood.model.*"    %>   
<%@ page import="edu.edgewood.dao.*"    %>  
<%@ page import="edu.edgewood.svc.*"    %>  
<%@ page import="java.util.*"    %> 

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

	<div id='header'>
	<h1>UACS-417-ADVENCED WEB DEVELOPMENT-SPRING-2019</h1>
		<div id='menu'>
		<%
			if (cookieExisit){
				MainPageDao dao = new MainPageDao();
				User loginUser = dao.getUserById(userId);
				String userName = loginUser.getFirstName() + " " + loginUser.getLastName();		
		%>		
			<a id='logoutLnkId'>Logout</a>
            
        <% }else{%>	
        
        	<a id='loginLnkId' href=''>Login</a>				
			<a id='RegisterLnkId' href=''>Register</a>
		<%} %>
				
		</div>
			
	</div>