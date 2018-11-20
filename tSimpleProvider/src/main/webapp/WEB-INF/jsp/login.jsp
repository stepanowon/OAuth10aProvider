<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function login() {
	document.getElementById("f1").submit();	
}
</script>
<body>
	<h2>로그인 페이지</h2><hr>
	<form id="f1" name="f1" method="post" action="login">
		계정 : <input type="text" id="userid" name="userid" value="" style="width:100px;"><br/>
		암호 : <input type="password" id="password" name="password" value="" style="width:100px;"><br/>
		<br/>
		<input type="button" value="로그인" onclick="login()">
	</form>
</body>
</html>
<% if (request.getAttribute("isLogin") != null) {  		%>
<script type="text/javascript">
	alert('로그인 실패!');
</script>
<% } %>



