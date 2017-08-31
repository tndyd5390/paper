<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.paper.dto.Notice_infoDTO" %>
<%@ page import="com.paper.util.CmmUtil" %>
<%
	Notice_infoDTO nDTO = (Notice_infoDTO) request.getAttribute("nDTO");
%>
<html>
<head>
<style>
.chats .by-me .chat-content {
	margin-left: 0px;
}

.chats .by-me .chat-meta {
	font-size: 20px;
	color: #999;
	font-weight: bold;
}

.btn-merge {
	width: 100px;
	font-size: 17px;
}
</style>

<script>
function doSubmit(f){
	if(confirm("병합 하시겠습니까?")){
		f.submit();
		window.close;
		return true;
	}else{
		return false;
	}
}
</script>
<%@include file="/include/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>병합파일 다운로드</title>
</head>
<body>
	<div class="row">

		<div class="col-lg-9 col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-flag-o red"></i><strong><%=nDTO.getNotice_title() %></strong>
					</h2>
				</div>
				<!-- <form name="f" action="mergeDocxProc.do" method="post" onsubmit='return doSubmit(this)'> -->
				<div class="panel-body">
					다운로드 들어간다
										<br>
					<%if(nDTO.getFile_name().equals("")){%>
						<strong>병합된 파일이 없습니다.</strong>
					<% }else{%>
					<form action="download.do" method="post">
						<input type="hidden" name="path" value="<%=CmmUtil.nvl(nDTO.getFile_path())%>">
						<input type="hidden" name="fileName" value="<%=CmmUtil.nvl(nDTO.getFile_name())%>">
						<input type="hidden" name="fileOrgName" value="<%=CmmUtil.nvl(nDTO.getNotice_title()) %>">
						<input type="submit" class="btn btn-info btn-merge" value="다운로드">
					</form>
					<%}%>
					
					<div class="widget-foot">
						<center>
						<!-- <button type="submit" class="btn btn-info btn-merge">병합</button> -->
						</center>
					</div>
				</div>
				<!-- </form> -->

			</div>

		</div>
		<!--/col-->

		<%@include file="/include/bottomJavaScript.jsp"%>
</body>
</html>