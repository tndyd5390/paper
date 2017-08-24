<%@page import="java.util.ArrayList"%>
<%@page import="com.paper.dto.User_infoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<User_infoDTO> uList = (List<User_infoDTO>)request.getAttribute("uList");
	if(uList == null) uList = new ArrayList();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%for(User_infoDTO uDTO : uList){
  	out.println(uDTO.getReg_user_no());
  	out.println(uDTO.getUser_name());
  	out.println(uDTO.getEmail());
  }
%>asdfasdf
</body>
</html>