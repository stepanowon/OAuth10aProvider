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
		if (confirm('�Է°��� �ѹ� �� Ȯ�����ּ���.\n\n����ұ��?')) {
			form.submit();
		}
	}
</script>
<body>
	<h1>Application ��� ������</h1>
	<hr>
	<form id="f1" name="f1" method="post" action="createApp">
	User Info : <%= usersVO.getUserid() %>(<%=usersVO.getUsername() %>)<br/>
	Application Name : <input type="text" id="appName" name="appName" value="Test Application" style="width:250px;"><br/>
	Callback Url : <input type="text" id="callbackUrl" name="callbackUrl" value="http://localhost:8000/Test" style="width:350px;"><br/>
	<br/>
	<input type="button" value="Application ���" onclick="createApp()" />
	<input type="button" value="Application List ��������" onclick="location.href='viewAppList';" >
	</form>
</body>
</html>