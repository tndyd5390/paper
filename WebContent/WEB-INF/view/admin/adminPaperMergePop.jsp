<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.paper.dto.Paper_infoDTO"%>
<%@ page import="com.paper.util.CmmUtil"%>
<%@ page import="java.util.List"%>
<%
	List<Paper_infoDTO> pList = (List) request.getAttribute("pList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/include/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function(){
		  $( "#paperList" ).sortable();
		  $( "#paperList" ).disableSelection();
		
		
	})
	function close() {
		self.close(); //자기자신창을 닫습니다.
	}
</script>

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
<title>논문 병합</title>
</head>
<body>
	<div class="row">

		<div class="col-lg-9 col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-flag-o red"></i><strong>논문 병합</strong>
					</h2>
				</div>
				<form name="f" action="mergeDocxProc.do" method="post">
				<input type="hidden" name="nNo" value="<%=pList.get(0).getNotice_no()%>">
				<div class="panel-body">
					<ul class="chats" id="paperList">
						<%
							for (Paper_infoDTO pDTO : pList) {
						%>
						<li class="by-me">
							<div class="chat-content">
								<div class="chat-meta"><%=pDTO.getPaper_kor() %></div>
								<%=pDTO.getUser_name() %>
								<div class="clearfix"><b><%=pDTO.getPaper_type() %></b></div>
								<input type="hidden" name="fileName" value="<%=pDTO.getFile_path()%><%=pDTO.getFile_name()%>">
							</div>
						</li>
						<%
							}
						%>
					</ul>
					
					<div class="widget-foot">
						<center>
							<button type="submit" class="btn btn-info btn-merge">병합</button>
						</center>
					</div>
				</div>
				</form>

			</div>

		</div>
		<!--/col-->

		<%@include file="/include/bottomJavaScript.jsp"%>
</body>
</html>