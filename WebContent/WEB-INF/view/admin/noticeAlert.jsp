<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	String url = (String)request.getAttribute("url");
%>
<html>
<script type="text/javascript">
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>소라네 컵밥</title>
</head>
<body>

</body>
</html>