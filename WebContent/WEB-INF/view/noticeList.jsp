<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		margin-top : 15px;
		width:100px;
		height: 40px;
		font-size: 18px;
	}
</style>
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
					<select class="form-control selectForm" placeholder="공고 제목이 들어가는 자리입니다.">
						<option>첫번쨰 공고</option>
						<option>두번째 공고</option>
						<option>세번째 공고</option>					
					</select>
					<div style="float: right">
						<button type="submit" class="btn btn-info submit-btn">제출</button>
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