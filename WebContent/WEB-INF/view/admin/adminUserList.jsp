<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.paper.dto.User_infoDTO"%>
<%@page import="com.paper.util.CmmUtil"%>
<%   
	List<User_infoDTO> uList = (List) request.getAttribute("uList");
	String userNo = (String)session.getAttribute("ss_user_no");
	if(CmmUtil.nvl(userNo).equals("")){
		response.sendRedirect("userLogin.do");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<<html>
<%@include file="/include/head.jsp"%>
<script type="text/javascript">
	function check() {
		var f = document.getElementById("f");
		var cbox = f.del_check;
		if (cbox.length) {
			for (var i = 0; i < cbox.length; i++) {
				cbox[i].checked = f.all.checked;
			}
		} else {
			cbox.checked = f.all.checked;
		}
	}
	function userdel_check() {
		console.log("userdel check()");
		var checked = false;
		var check = document.getElementsByName("del_check");
		var f = document.getElementById("f");
		if (check.length) {
			for (var i = 0; i < check.length; i++) {
				if (check[i].checked) {
					checked = true;
					break;
				}
			}
		}
		if (checked) {
			if (confirm("선택한 것을 삭제하시겠습니까?")) {
				f.submit();
			}
		} else {
			alert("하나도 선택된 것이 없습니다.");
		}
	}
	$(
			function() {
				$('#userSearch')
						.keyup(
								function() {
									var word = $('#userSearch').val();
									$
											.ajax({
												url : "userSearch.do",
												method : "post",
												data : {
													'word' : word
												},
												dataType : "json",
												success : function(data) {
													var contents = "";
													$
															.each(
																	data,
																	function(
																			key,
																			value) {
																		contents += "<tr>"
																		contents += "<td><center> <input type='checkbox' name='delCheck' id='inlineCheckbox1' value='"+value.user_no+"'"+">"
																		contents += "<td align=center>"
																				+ value.user_no
																				+ "</td>"
																		contents += "<td align=center> <a href='adminUserDetail.do?unum="
																				+ value.user_no
																				+ "'>"
																				+ value.email
																				+ "</a> </td>"
																		contents += "<td align=center>"
																				+ value.user_name
																				+ "</td>"
																		contents += "<td align=center>"
																				+ value.belong
																				+ "</td>"
																		contents += "<td align=center>"
																				+ value.phone
																				+ "</td>"
																		contents += "<td align=center>"
																				+ value.reg_dt
																				+ "</td>"
																		contents += "</tr>"
																	})
													$('#userList').html(
															contents);
												}
											})
								})
			})
</script>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>회원정보</title>
</head>
<body>
	<form action="adminUserCheckedDelete.do" method="post" id="f">
		<%@include file="/include/naviBarAndasideBar.jsp"%>
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">
							<i class="icon_profile"></i> 회원 관리
						</h3>
						<ol class="breadcrumb">
							<li><i class="fa fa-home"></i><a href="orderList.do">메인 화면</a></li>
							<li><i class="icon_profile"></i>회원 관리</li>
						</ol>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading">회원 목록
								<div class="navbar-form pull-right">
									검색 : <input class="form-control" placeholder="Search" type="text" id="userSearch">
								</div>
							</header>
							<div class="panel-body">
								<div class="table-borderd">
									<table class="table" align="center">
										<thead>
											<tr>
												<th><center><input type="checkbox" id="inlineCheckbox1" name="all" value="option1" onclick="check();"></center></th>
												<th><center>회원번호</center></th>
												<th><center>아이디</center></th>
												<th><center>회원이름</center></th>
												<th><center>소속</center></th>
												<th><center>연락처</center></th>
												<th><center>가입일</center></th>
											</tr>
										</thead>
									<tbody id="userList">
										<%
											for (User_infoDTO udto : uList) {
										%>
										<tr>
											<td align=center><input type="checkbox" name="del_check" id="inlineCheckbox1" value="<%=CmmUtil.nvl(udto.getUser_no())%>"></td>
											<td align=center><%=CmmUtil.nvl(udto.getUser_no())%></td>
											<td align=center><a href="adminUserDetail.do?unum=<%=CmmUtil.nvl(udto.getUser_no())%>"><%=CmmUtil.nvl(udto.getEmail())%></a></td>
											<td align=center><%=CmmUtil.nvl(udto.getUser_name())%></td>
											<td align=center><%=CmmUtil.nvl(udto.getBelong())%></td>
											<td align=center><%=CmmUtil.nvl(udto.getPhone())%></td>
											<td align=center><%=CmmUtil.nvl(udto.getReg_dt())%></td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</section>
					<div style="float: right">
						<a class="btn btn-info" href="#" title="클릭하시면 선택항목이 삭제됩니다." onclick="userdel_check()">삭제</a>
					</div>
				</div>
			</section> <%@ include file="/include/bottomJavaScript.jsp"%>
		</form>
	</body>
</html>
