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
	<form class="form">
		<section id="main-content"> <section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="icon_document_alt"></i>공고 관리
				</h3>
				<ol class="breadcrumb">
					<li><i class="icon_document_alt"></i><a href="#">공고 리스트</a></li>
					<li><i class="icon_document_alt"></i><a href="#">공고 등록</a></li>
					<li><i class="icon_document_alt"></i>공고 제출</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<section class="panel"> <header class="panel-heading">
				공고 제출 </header>
				<div class="panel-body">
					<div class="form-group">
						<label>한글 제목 <a>*</a></label> <input type="text" class="form-control Sub-Txt">
						<hr>
					</div>
					<div class="form-group">
						<label>영문 제목 <a>*</a> </label> <input type="text" class="form-control Sub-Txt">
						<hr>
					</div>
					<div class="form-group">
						<label class="block">논문 첨부 <a>*</a></label> <input type="text"
							class="form-control subTxt Sub-Txt"><a
							class="btn btn-info subBtn" href="#" title="클릭하시면 등록 화면으로 이동합니다.">파일
							등록</a> 
						<hr>
					</div>
					<div class="form-group">
						<label>저자 정보 <a>*</a></label> 
						<input type="text" placeholder="이름 입력" class="form-control Sub-Txt">
						<input type="text" placeholder="이메일 입력" class="form-control Sub-Txt">
						<input type="text" placeholder="소속 입력" class="form-control Sub-Txt">
						<hr>
					</div>
					<div class="form-group">
						<label>저자 정보 <a>*</a></label> 
						<input type="text" placeholder="이름 입력" class="form-control Sub-Txt">
						<input type="text" placeholder="이메일 입력" class="form-control Sub-Txt">
						<input type="text" placeholder="소속 입력" class="form-control Sub-Txt">
						<hr>
					</div>
					<div class="form-group">
						<label>저자 정보 <a>*</a></label> 
						<input type="text" placeholder="이름 입력" class="form-control Sub-Txt">
						<input type="text" placeholder="이메일 입력" class="form-control Sub-Txt">
						<input type="text" placeholder="소속 입력" class="form-control Sub-Txt">
						<hr>
					</div>
					<div class="form-group btn-right">
						<a class="btn btn-primary">추가</a>
						<a class="btn btn-default">취소</a>
					</div>
					</div>
				</div>
			</div>
					<a class="btn btn-primary btn-right">제출 하기</a>
		</section>
		</section>
		</section>
	</form>
	<%@include file="/include/bottomJavaScript.jsp"%>
</body>
</html>