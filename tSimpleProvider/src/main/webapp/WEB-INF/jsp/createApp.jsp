<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.sds.testprovider.model.*" %>
<%
	UsersVO usersVO = (UsersVO)session.getAttribute("usersVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function createApp() {
		var form = document.getElementById("f1");
		if (confirm('입력값을 한번 더 확인해주세요.\n\n등록할까요?')) {
			form.submit();
		}
	}
</script>
<body>
	<h1>Application 등록 페이지</h1>
	<hr>
	<form id="f1" name="f1" method="post" action="createApp">
	User Info : <%= usersVO.getUserid() %>(<%=usersVO.getUsername() %>)<br/>
	Application Name : <input type="text" id="appName" name="appName" value="Test Application" style="width:250px;"><br/>
	Callback Url : <input type="text" id="callbackUrl" name="callbackUrl" value="http://localhost:8000/Test" style="width:350px;"><br/>
	<br/>
	<input type="button" value="Application 등록" onclick="createApp()" />
	<input type="button" value="Application List 페이지로" onclick="location.href='viewAppList';" >
	</form>
</body>
</html>