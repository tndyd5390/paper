<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.paper.util.CmmUtil" %>
<%

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@include file="/include/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>소라네 컵밥 회원 정보 찾기</title>
</head>
<script type="text/javascript">
function findCheck() {
	var email = $('#email');
	var name = $('#name');
	var phone = $('#phone');
	
	if(email.val() ==""){
		alert("이메일을 입력하세요.");
		email.focus();
		return false;
	}else if(name.val()==""){
		alert("이름을 입력하세요.");
		password.focus();
		return false;
	}else if(phone.val()==""){
		alert("핸드폰 번호를 입력하세요.");
		phone.focus();
		return false;
	}else{
		return true
	}
}
</script>
<body>
	<div class="col-sm-12" align=center>
		<form class="login-form" action="userFindPw.do" method="post" name="password1" onsubmit="return findCheck();">
			<div class="login-wrap">
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_mail_alt"></i></span>
					<input type="email" name="email" class="form-control" placeholder="이메일" id="email">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<input type="text" name="user_name" class="form-control" placeholder="이름" id="name">
				</div>
				<div class="input-group">
					<span class="input-group-addon"><i class="icon_calendar"></i></span>
					<input type="text" name="phone" class="form-control"	placeholder="전화번호 ex)01012341234" onkeyup="return filterNumber(this);" id="phone">
				</div>
				<button class="btn btn-primary btn-lg btn-block" type="submit">비밀번호 찾기</button>
			</div>
		</form>
	</div>

	</section>
	</section>

	<script type="text/javascript">
    function filterNumber(obj) 
    {
        var key = event.keyCode;
        if(!(key==8||key==9||key==144||key==46||key==110||(key >= 48 && key <=57)||(key >= 96 && key <= 105))) 
        {
            alert("숫자만 입력 가능합니다.")
            document.password1.birthday.value=""; 
            event.returnValue = false;
        }
    	
/*         if (event.keyCode >= 48 && event.keyCode <= 57) { //only number
            return true;
        } else {
        	if(event.keyCode == 13){
        		action();
        	}
			event.returnValue = false;        	
            alert("숫자만 입력 가능합니다.")
        } */
    }
	</script>

	<%@include file="/include/bottomJavaScript.jsp"%>
</body>

</html>
