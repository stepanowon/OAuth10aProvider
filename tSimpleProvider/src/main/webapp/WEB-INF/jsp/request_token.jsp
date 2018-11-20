<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String message = (String)request.getAttribute("message");
	boolean isValid = (Boolean)request.getAttribute("isValid");
	if (isValid) {
		//response.setContentType("application/x-www-form-urlencoded");
		response.setContentType("text/plain");
		response.getWriter().println(message);
	} else {
		response.setContentType("text/html");
%>
	<h1>InValid Request!!</h1><hr>
	<br>
	<h3><%=message %></h3>
<%
	}
%>
