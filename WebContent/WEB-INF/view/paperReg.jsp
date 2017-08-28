<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/include/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
label{
	font-size:20px;
	font-weight: bold;
}

input.subTxt {
	width: 93%;
	display: inline;
}

a.subBtn {
	margin-left: 10px;
	margin-right: 10px;
	margin-bottom:5px;
}

label.block {
	display: block;
}

input.Sub-Txt{
	margin-bottom:5px;
}
.btn-right{
	float: right;
}

</style>

<script>


var writerCount = 1;
function writerAdd(){
	writerCount++;
	var writerForm = "";
	writerForm += "<div class='form-group' id='form"+writerCount+"'>";
	writerForm += "<label>저자정보 <a>*</a></label>";
	writerForm += "<input type='text' placeholder='이름 입력' class='form-control Sub-Txt' name='name'>";
	writerForm += "<input type='text' placeholder='이메일 입력' class='form-control Sub-Txt' name='email'>";
	writerForm += "<input type='text' placeholder='소속 입력' class='form-control Sub-Txt'name='belong'>";
	writerForm += "<a class='btn btn-default' onclick='writerDel("+writerCount+")'>제거</a>";
	writerForm += "<hr>";
	writerForm += "</div>";
	
	$('#writerGroup').append(writerForm);
}
function writerDel(count){
	writerCount--;	
	$('#form'+count).remove();
}
function doSubmit(f){
	
	if(confirm("제출하시겠습니까?")){
		$('#writerSize').val(writerCount);
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
	<form class="form" action="paperRegProc.do" method="post" name="f" onsubmit='return doSubmit(this)'>
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
						<label>한글 제목 <a>*</a></label> <input type="text" class="form-control Sub-Txt" name="korName">
						<hr>
					</div>
					<div class="form-group">
						<label>영문 제목 <a>*</a> </label> <input type="text" class="form-control Sub-Txt" name="engName">
						<hr>
					</div>
					<div class="form-group">
						<label class="block">논문 첨부 <a>*</a></label> <input type="file"
							class="form-control subTxt Sub-Txt" name="paper">
						<hr>
					</div>
					<div id="writerGroup">
						<div class="form-group" id="form1">
							<label>저자 정보 <a>*</a></label> 
							<input type="text" placeholder="이름 입력" class="form-control Sub-Txt" name="name">
							<input type="text" placeholder="이메일 입력" class="form-control Sub-Txt" name="email">
							<input type="text" placeholder="소속 입력" class="form-control Sub-Txt" name="belong">
							<hr>
						</div>
					</div>
					<div class="form-group btn-right">
						<a class="btn btn-primary" onclick="writerAdd()">추가</a>
					</div>
					</div>
				</div>
				<input type="hidden" name="writerSize" id="writerSize">
			</div>
			<input type="submit" class="btn btn-primary btn-right" value="제출 하기" >
		</section>
		</section>
		</section>
	</form>
	<%@include file="/include/bottomJavaScript.jsp"%>
</body>
</html>