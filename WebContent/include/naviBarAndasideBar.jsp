<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "com.paper.util.CmmUtil"  %>
<% 
	String ss_user_name = CmmUtil.nvl((String) session.getAttribute("ss_user_name"));
	String ss_user_no = CmmUtil.nvl((String) session.getAttribute("ss_user_no"));
	String ss_user_auth = CmmUtil.nvl((String) session.getAttribute("ss_user_auth"));
%>
<script type="text/javascript">
	function logOut(){
		if(confirm("로그아웃 하시겠습니까?")){
			location.href="logout.do";
		}
	}
</script>
<section id="container" class=""> <header
		class="header dark-bg">
	<div class="toggle-nav">
		<div class="icon-reorder tooltips"
			data-original-title="Toggle Navigation" data-placement="bottom">
			<i class="icon_menu"></i>
		</div>
	</div>
	<%if(ss_user_auth.equals("A")){ %>
	 		<a href="adminNoticeList.do" class="logo">논<spanclass="lite">문</span></a> 
	<%}else{ %>
		 	<a href="noticeList.do" class="logo">논<spanclass="lite">문</span></a> 
	<%} %>

	<div class="nav search-row" id="top_menu">
		<!--  search form start -->
		<ul class="nav top-menu">
			<li>
				<!-- <form class="navbar-form">
					<input class="form-control" placeholder="Search" type="text">
				</form> -->
			</li>
		</ul>
		<!--  search form end -->
	</div>

	<div class="top-nav notification-row">
		<!-- notificatoin dropdown start-->
		<ul class="nav pull-right top-menu">

			<!-- task notificatoin start -->
			<!-- <li id="task_notificatoin_bar" class="dropdown"><a
				data-toggle="dropdown" class="dropdown-toggle" href="#"> <i
					class="icon-task-l"></i> <span class="badge bg-important">6</span>
			</a> -->
				<!-- <ul class="dropdown-menu extended tasks-bar">
					<div class="notify-arrow notify-arrow-blue"></div>
					<li>
						<p class="blue">You have 6 pending letter</p>
					</li>
					<li><a href="#">
							<div class="task-info">
								<div class="desc">Design PSD</div>
								<div class="percent">90%</div>
							</div>
							<div class="progress progress-striped">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="90" aria-valuemin="0"
									aria-valuemax="100" style="width: 90%">
									<span class="sr-only">90% Complete (success)</span>
								</div>
							</div>
					</a></li>
					<li><a href="#">
							<div class="task-info">
								<div class="desc">Project 1</div>
								<div class="percent">30%</div>
							</div>
							<div class="progress progress-striped">
								<div class="progress-bar progress-bar-warning"
									role="progressbar" aria-valuenow="30" aria-valuemin="0"
									aria-valuemax="100" style="width: 30%">
									<span class="sr-only">30% Complete (warning)</span>
								</div>
							</div>
					</a></li>
					<li><a href="#">
							<div class="task-info">
								<div class="desc">Digital Marketing</div>
								<div class="percent">80%</div>
							</div>
							<div class="progress progress-striped">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
									style="width: 80%">
									<span class="sr-only">80% Complete</span>
								</div>
							</div>
					</a></li>
					<li><a href="#">
							<div class="task-info">
								<div class="desc">Logo Designing</div>
								<div class="percent">78%</div>
							</div>
							<div class="progress progress-striped">
								<div class="progress-bar progress-bar-danger" role="progressbar"
									aria-valuenow="78" aria-valuemin="0" aria-valuemax="100"
									style="width: 78%">
									<span class="sr-only">78% Complete (danger)</span>
								</div>
							</div>
					</a></li>
					<li><a href="#">
							<div class="task-info">
								<div class="desc">Mobile App</div>
								<div class="percent">50%</div>
							</div>
							<div class="progress progress-striped active">
								<div class="progress-bar" role="progressbar" aria-valuenow="50"
									aria-valuemin="0" aria-valuemax="100" style="width: 50%">
									<span class="sr-only">50% Complete</span>
								</div>
							</div>

					</a></li>
					<li class="external"><a href="#">See All Tasks</a></li>
				</ul></li> -->
			<!-- task notificatoin end -->
			<!-- user login dropdown start-->
			<li class="dropdown"><a data-toggle="dropdown"
				class="dropdown-toggle" href="#"> <span class="profile-ava">
				</span> <span class="username"><%=ss_user_name%></span> <b class="caret"></b>
			</a>
				<ul class="dropdown-menu extended logout">
					<div class="log-arrow-up"></div>
					<li class="eborder-top"><a href="#"><i
							class="icon_profile"></i> 프로필</a></li>
					<li><a href="#" onclick="logOut();"><i class="icon_key_alt"></i> 로그아웃</a></li>
				</ul></li>
			<!-- user login dropdown end -->
		</ul>
		<!-- notificatoin dropdown end-->
	</div>
	</header> <!--header end--> <!--sidebar start--> <aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu">
		<%if(ss_user_no.equals("")||ss_user_no==null){ %>
		
		<%}else if(ss_user_auth.equals("U")||ss_user_auth=="U"){%>
			<li class="active"><a class="" href="noticeList.do"> <i
					class="icon_house_alt"></i> <span>공고 리스트</span>
			</a></li>
		<%}else{ %>
				<li class="active"><a class="" href="adminNoticeList.do"> <i
					class="icon_house_alt"></i> <span>공고 리스트</span>
			</a></li>
			<li><a class="" href="adminUserList.do"> <i
					class="icon_profile"></i> <span>회원 리스트</span>
			</a></li>
		<%} %>
		</ul>
		<!-- sidebar menu end-->
	</div>
	</aside>