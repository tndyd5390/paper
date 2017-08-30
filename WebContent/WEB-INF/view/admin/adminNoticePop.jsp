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
<style>
.chats .by-me .chat-content {
    margin-left: 0px;
}
.chats .by-me .chat-meta {
    font-size: 20px;
    color: #999;
    font-weight: bold;
}
	
</style>

<title>공고 리스트 팝업</title>
</head>
<body>
  <div class="row">
            <div class="col-md-4 portlets">
              <!-- Widget -->
              <div class="panel panel-default">
				<div class="panel-heading">
                  <div class="pull-left">공고 리스트</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>  
                  <div class="clearfix"></div>
                </div>

                <div class="panel-body">
                  <!-- Widget content -->
                  <div class="padd sscroll">
                    
                    <ul class="chats">

                      <!-- Chat by us. Use the class "by-me". -->
                      <li class="by-me">
                        <!-- Use the class "pull-left" in avatar -->
                        <div class="chat-content">
                          <!-- In meta area, first include "name" and then "time" -->
                          <div class="chat-meta">공고 1 </div>
                          내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발
                          <div class="clearfix"></div>
                        </div>
                      </li> 

                      <!-- Chat by other. Use the class "by-other". -->
                      <li class="by-me">
                        <!-- Use the class "pull-right" in avatar -->

                        <div class="chat-content">
                          <!-- In the chat meta, first include "time" then "name" -->
                          <div class="chat-meta">공고 2</div>
                          내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발
                          <div class="clearfix"></div>
                        </div>
                      </li>   

                      <li class="by-me">

                        <div class="chat-content">
                          <div class="chat-meta">공고 3</div>
                    내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발
                          <div class="clearfix"></div>
                        </div>
                      </li>  

                      <li class="by-me">
                        <!-- Use the class "pull-right" in avatar -->
                        <div class="chat-content">
                          <!-- In the chat meta, first include "time" then "name" -->
                          <div class="chat-meta">공고 4</div>
                          내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발 내용 씨발
                          <div class="clearfix"></div>
                        </div>
                      </li>                                                                                  

                    </ul>

                  </div>
            </div>
       </div>
   </div>
</div>
<%@include file="/include/bottomJavaScript.jsp"%>
</body>
</html>