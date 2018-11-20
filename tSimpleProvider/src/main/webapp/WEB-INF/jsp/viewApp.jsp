<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.sds.testprovider.model.*" %>
<%
	ConsumerVO vo = (ConsumerVO)request.getAttribute("oAuthKeyVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>Application 정보</h1>
<hr/>
Application Name : <%=vo.getAppName() %><br/>
등록된 날짜 : <%=vo.getRegDate() %><br/>
Consumer Key : <%=vo.getConsumerKey() %><br/>
Consumer Secret : <%=vo.getConsumerSecret() %><br/>
Callback Url : <%=vo.getCallbackUrl() %><br/>
<br/><br/>
<a href="viewAppList">Application List 목록으로</a>
</body>
</html>