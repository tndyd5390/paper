<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "com.paper.util.CmmUtil" %>
<%@ page import = "com.paper.dto.Notice_infoDTO" %>
<%@ page import = "java.util.List" %>
<% 
	List<Notice_infoDTO> nList = (List) request.getAttribute("nList");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/include/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	select.selectForm{
		height:40px;
		font-size:18px;
	}
	a.submit-btn{
		width:100px;
		height: 40px;
		font-size: 18px;
	}
</style>
<script>
	$(function(){
		$('#Select-Notice').click(function(){
			var nNo = $('#Select-Notice option:selected').attr('id');
			if(nNo != "mainSelect"){
			$.ajax({
				url : "selectNotice.do",
				method : "post",
				data : {
					'nNo' : nNo
				},
				dataType : "json",
				success : function(data) {
					var contents ="";
					$.each(data,function(key,value) {
					 	contents += value
					})
					console.log(data);
					$('#append-contents').html(contents);
				}
			})
			}
		})
		
	})

</script>
<title>공고 등록</title>
</head>
<body>
	<%@include file="/include/naviBarAndasideBar.jsp"%>
	<form name="f" action="#" method="post">
		<section id="main-content"> <section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="icon_document_alt"></i>공고 관리
				</h3>
				<ol class="breadcrumb">
					<li><i class="icon_document_alt"></i><a href="#">공고 리스트</a></li>
					<li><i class="icon_document_alt"></i>공고 등록</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<section class="panel"> <header class="panel-heading">
				공고 등록 </header>
				<div class="panel-body">
					<h2>
						공고 선택 *
					</h2>
					<select id="Select-Notice" class="form-control selectForm" placeholder="공고 제목이 들어가는 자리입니다.">
						<option id="mainSelect">-------선택하세요-------</option>
					<%
						for(Notice_infoDTO nDTO : nList){
					%>
						<option id="<%=nDTO.getNotice_no()%>"><%=nDTO.getNotice_title() %> / 접수시작 : <%=nDTO.getReception_date() %> / 마감 <%=nDTO.getEnd_date() %></option>
					<%
						} 
					%>
					</select>
					<div style="float: right; margin-top:30px">
						<button type="submit" class="btn btn-info submit-btn">제출</button>
					</div>
					<div id="append-contents">
					
					</div>
				</div>
				</section>
			</div>
		</div>
		</section> </section>
	</form>
	<%@include file="/include/bottomJavaScript.jsp"%>
</body>
</html>