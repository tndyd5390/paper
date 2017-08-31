<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String link = "download.do?path=" +
			"C:\\Users\\Data8311-06\\git\\paper\\WebContent\\papers\\&fileName=test.docx&realFileName=aaa.docx";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="download.do" mehtod="post">
	<input type="hidden" name="path" value="C:\\Users\\Data8311-06\\git\\paper\\WebContent\\papers\\">
	<input type="hidden" name="fileName" value="aaa.docx">
	<input type="submit" value="다운">
</form>
</body>
</html>