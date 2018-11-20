<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="java.util.*" %>    
<%
	Exception ex = (Exception)request.getAttribute("exception");
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Oh My God!!</title>
</head>
<body>
  OAuthException : <%= ex.getMessage() %>
</body>
</html>