<!-- created by Yanjun -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="edu.edgewood.model.*"    %>   
<%@ page import="edu.edgewood.dao.*"    %>  
<%@ page import="edu.edgewood.svc.*"    %>  
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.*" %> 


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>View Detail</title>
		<link rel="stylesheet" href="css/common.css"/>
		<Script src='js/ext/jquery-3.3.1.min.js'></Script>
		<Script src='js/common.js'></Script>
		<Script src='js/details.js'></Script>
	</head>
	<body>	
	
		<div id='container'>	
		
			<%@include file="/WEB-INF/jsp/include/header.jsp" %>
			
			<div id='content'>
			<form>			
				<p><b>${requestScope.posting.createdDate} </b> </p>
				<p>${requestScope.posting.title} </p>
				<p>${requestScope.posting.shortDescription} </p>
				<p>${requestScope.posting.longDescription} </p>
						
			 <input type='button' id='backBtnId' value='Back'/>
			</form>
			
			</div>
			
			<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
		
		
	</div>
	</body>
</html>