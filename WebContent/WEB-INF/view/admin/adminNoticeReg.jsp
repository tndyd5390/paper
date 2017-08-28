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

<script>


function doSubmit(f){
	
	if(confirm("제출하시겠습니까?")){
		f.submit();
		
		return true;
	}else{
		return false;
	}
	
}

</script>
<title>공고 등록</title>
</head>
<body>
	<%@include file="/include/naviBarAndasideBar.jsp"%>
	<form class="form" action="noticeRegProc.do" method="post" name="f" onsubmit='return doSubmit(this)'>
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
						<label>제목 <a>*</a></label> <input type="text" class="form-control Sub-Txt" name="title">
						<hr>
					</div>
					<div class="form-group">
						<label class="block">접수시작일 <a>*</a></label> <input type="date"
							class="form-control subTxt Sub-Txt" name="reception_date">
						<hr>
					</div>
					<div class="form-group">
						<label class="block">접수마감일 <a>*</a></label> <input type="date"
							class="form-control subTxt Sub-Txt" name="end_date">
						<hr>
					</div>
					<div class="form-group">
						<label class="block">개최일 <a>*</a></label> <input type="date"
							class="form-control subTxt Sub-Txt" name="exhibition_date">
						<hr>
					</div>
					
					</div>
				</div>
			</div>
			<input type="submit" class="btn btn-primary btn-right" value="등록" >
		</section>
		</section>
		</section>
	</form>
	<%@include file="/include/bottomJavaScript.jsp"%>
</body>
</html>