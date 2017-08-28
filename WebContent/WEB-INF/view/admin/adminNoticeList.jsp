<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.paper.dto.Notice_infoDTO" %>
<%@ page import = "com.paper.util.CmmUtil" %>
<%@ page import = "java.util.List" %>
<%
	List<Notice_infoDTO> nList = (List) request.getAttribute("nList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/include/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function check(){
		var f = document.getElementById("f");
		var cbox = f.del_check;
		if(cbox.length){
			for(var i = 0; i< cbox.length;i++){
				cbox[i].checked = f.allCheck.checked;
			}
		} else {
			cbox.checked = f.all.checked;
		}
	}
	function userdel_check(){
		var checked=false;
		var check = document.getElementsByName("del_check");
		var f = document.getElementById("f");
		
		if(check.length){
			for(var i = 0; i< check.length; i++){
				if(check[i].checked){
					checked = true;
					break;
				}				
			}
		}
		if(checked){
			if(confirm("선택한 것을 삭제하시겠습니까?")){
				f.submit();
			}
		}else{
			alert("선택된 것이 없습니다.");
		}
	}
</script>
<title>공고 리스트</title>
</head>
<body>
<%@include file="/include/naviBarAndasideBar.jsp"%>
<section id="main-content"> <section class="wrapper">
<form method="post" id="f" action="adminNoticeCheckedDelete.do">
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="icon_document_alt"></i>공고 관리
				</h3>
				<ol class="breadcrumb">
					<li><i class="icon_document_alt"></i>공고 리스트</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<section class="panel"> <header class="panel-heading">
				공고 등록 </header>
				<div class="panel-body">                          
				<table class="table table-striped">
                              <thead>
                              <tr>
                                  <th><input type="checkbox" name="allCheck" onclick="check();"></th>
                                  <th>번호</th>
                                  <th>제목</th>
                                  <th>접수시작일</th>
                                  <th>접수마감일</th>
                                  <th>개최일</th>
                              </tr>
                              </thead>
                              <tbody>
                              <%for(Notice_infoDTO nDTO : nList){ %>                            
                              <tr>
                              	  <td><input type="checkbox" name="del_check" value="<%=nDTO.getNotice_no()%>"></td>
                                  <td><%=nDTO.getNotice_no() %></td>
                                  <td><%=nDTO.getNotice_title() %></td>
                                  <td><%=nDTO.getReception_date() %></td>
                                  <td><%=nDTO.getEnd_date() %></td>
                                  <td><%=nDTO.getExhibition_date() %></td>
                              </tr>
                              <%} %>
                              </tbody>
                          </table>
							<a class="btn btn-primary btn-right" href="adminNoticeReg.do">등록</a>
							<input type="button" class="btn btn-danger btn-right" value="삭제" onclick="userdel_check()">
				</div>
				</section>
			</div>
		</div>
		</form>
		</section> </section>

<%@include file="/include/bottomJavaScript.jsp"%>
</body>
</html>