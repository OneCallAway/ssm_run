<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath", basePath);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
</head>

  </head>
  
  <body>
  	<%@ include file="top.jsp"%>
  	<table cellpadding="0" cellspacing="0" id="maintable">
        <tr>
            <td class="lefttd"><%@ include file="left.jsp"%></td>
            <td>
  		<table id="sqltable">
		                <tr class="title">
		                	<td>排名</td>
			               <td>试卷主题</td>
			                <td>考试学生</td>
			                <td>成绩</td>
		                </tr>
                		<c:forEach var="list"  items="${list}" varStatus="status">
		                <tr class="rows">
		                	<td>第${ status.index + 1}名</td>
			                <td>${list.exam.title}</td>
			                <td>${list.client.name}</td>
			                <td>${list.score}分</td>
		                </tr>
		                </c:forEach>
	                </table>
	                <div style="width:100%; margin-left:24px; background:white; text-align:center" id="pagediv">
						<style>
						#pagediv a,#pagediv a:visited{ color:#A51010; margin:5px; font-size:12px}
						</style>
				  		${pages }
				  		</div>
  	</td>
            <td class="mainrightborder"></td>
        </tr>
    </table>
    <%@ include file="foot.jsp"%>

  </body>
</html>
