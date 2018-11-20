<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.sds.testprovider.model.*" %>
<%@ page import="java.util.*" %>
<%
	List<ConsumerVO> list = (List<ConsumerVO> )request.getAttribute("list");
	UsersVO usersVO = (UsersVO)session.getAttribute("usersVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function goCreateApp() {
	location.href="createApp";	
}

function deleteApp(consumerKey) {
	location.href = "deleteApp?consumerkey=" + consumerKey;
}

function viewApp(consumerKey) {
	location.href = "viewApp?consumerkey=" + consumerKey;
}
</script>
<body>
<h2><%=usersVO.getUsername()%>(<%=usersVO.getUserid()%>) 님의 등록된 Application List</h2>
<hr />
<br />
<%
	if (list == null || list.size() == 0) {
%>
	<h1>등록된 Application이  없습니다.</h1>
<%
	} else {
%>
	<table border="1">
<%
	for (int i=0; i < list.size(); i++) { 
		   ConsumerVO vo = list.get(i);
%>
      <tr>		   
		   <td>	
		   		Application Name : <%=vo.getAppName() %><br/>
		   		User ID : <%=vo.getUserId() %><br />
		   		Callback Url : <%=vo.getCallbackUrl() %><br/>
		   		Registered Date : <%=vo.getRegDate() %><br/>
		   </td>
		   <td>
		   		<input type="button" value="Application 삭제" 
		   				onclick="deleteApp('<%= vo.getConsumerKey()%>')">
		   		<input type="button" value="Application 상세 정보" 
		   				onclick="viewApp('<%= vo.getConsumerKey()%>')">				
		   </td>
	   </tr>   
<% 		   
	   }
%>			
	</table>
<% } %>
    <br/>
	<input type="button" value="Application 등록 페이지로 이동" onclick="goCreateApp();">

</body>
</html>