<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.sds.testprovider.model.*" %>    
<%
	String errorMessage = (String)request.getAttribute("errorMessage");
	RequestTokenVO tokenVO = (RequestTokenVO)request.getAttribute("requestTokenVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>OAuth 접근 불허!!</h1>
	<hr>
	<%=errorMessage %>
<% if (tokenVO != null) { %>
	<br><br>
	<a href="<%=tokenVO.getCallback() %>">'<%=tokenVO.getAppName() %>' 애플리케이션으로 이동합니다.</a>
<% } %>
</body>
</html>