<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.sds.testprovider.util.*" %>
<%@ page import="com.sds.testprovider.model.*" %>

<%
	RequestTokenVO vo = (RequestTokenVO)request.getAttribute("requestTokenVO");
	//���� ó���� session���� �ϱ�� ����
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
	//���ΰźθ� ������ Provider���� �߱��ߴ� Request token ������ �����Ѵ�.
	//�� ���������� tbl_request_toke���̺���..����!
	document.getElementById("allow_deny").value="deny";
	document.getElementById("f1").submit();
}
</script>
<body>
<h2>Simple Provider Authorization!!</h2>
<hr>
<% if (loginResult != null && loginResult.equals("false")) { %>
	<div style="font-color: blue; font-size:13pt;">
		<b>�α��� ����!!! : ��Ȯ�� ID�� ��ȣ�� �Է��Ͻʽÿ�.</b>
	</div>
<% } %>	
	<div>
		<div style="font-size:16pt;">'<%=vo.getAppName() %>'�� ����� ���� ������ �����ϴ� ���� ����Ͻðڽ��ϱ�?</div>
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
			<div><%=usersVO.getUserid() %>(<%=usersVO.getUsername() %>) �� �α��� ��!!</div>
			<br/><br/>

<% } %>
      		<input type="button" value="���ø����̼� ����"  id="allow" onclick="goAllow();">
      		<input type="button" value="���� �ź�"  id="deny" onclick="goDeny();">		
		</form>
	</div>
	

</body>
</html>