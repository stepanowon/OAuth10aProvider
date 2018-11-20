<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.sds.testprovider.util.*" %>
<%@ page import="com.sds.testprovider.model.*" %>

<%
	RequestTokenVO vo = (RequestTokenVO)request.getAttribute("requestTokenVO");
	//인증 처리는 session으로 하기로 결정
	String loginResult = (String)request.getAttribute("loginResult");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>SimpleProvide</title>
</head>
<script type="text/javascript">
function goAllow() {
	document.getElementById("f1").submit();
}

function goDeny() {
	//승인거부를 누르면 Provider에서 발급했던 Request token 정보를 삭제한다.
	//이 예제에서는 tbl_request_toke테이블에서..삭제!
	document.getElementById("allow_deny").value="deny";
	document.getElementById("f1").submit();
}
</script>
<body>
<h2>Simple Provider Authorization!!</h2>
<hr>
<% if (loginResult != null && loginResult.equals("false")) { %>
	<div style="font-color: blue; font-size:13pt;">
		<b>로그인 실패!!! : 정확한 ID와 암호를 입력하십시오.</b>
	</div>
<% } %>	
	<div>
		<div style="font-size:16pt;">'<%=vo.getAppName() %>'이 당신의 계정 정보에 접근하는 것을 허용하시겠습니까?</div>
		<hr>
		<form id="f1" method="post" action="authorize">
			<input type="hidden" id="oauth_token" name="oauth_token" 
				value="<%=vo.getRequestToken() %>" />
			<input type="hidden" id="allow_deny" name="allow_deny" value="allow" /> 
<%  if (!SessionUtil.isLoginned(session)) { %>
			User ID : <input id="userid" name="userid" type="text" /><br/>
			Password : <input id="password" name="password" type="password" 
				value="" /><br/><br/>
<% } else { %>
<% UsersVO usersVO = SessionUtil.getUserInfo(session); %>			
			<div><%=usersVO.getUserid() %>(<%=usersVO.getUsername() %>) 님 로그인 중!!</div>
			<br/><br/>

<% } %>
      		<input type="button" value="애플리케이션 승인"  id="allow" onclick="goAllow();">
      		<input type="button" value="승인 거부"  id="deny" onclick="goDeny();">		
		</form>
	</div>
	

</body>
</html>