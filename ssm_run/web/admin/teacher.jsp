<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		                	<td>账号</td>
			               <td>姓名</td>
			                <td>职位</td>
			                <td>简介</td>
			                <td>操作</td>
		                </tr>
                		<c:forEach var="list"  items="${list}">
		                <tr class="rows">
		                	<td>${list.login}</td>
			                <td>${list.name}</td>
			                <td>${list.teachertype.type}</td>
			                <td><span title="${list.content }">${fn:substring(list.content,0,30) }...</span></td>
			                <td>
			                	<a href="${basePath_top }teacher/addpage.do?id=${list.id}">修改</a>
				                <a href="javascript:;;" id="${list.id }" class="del">注销</a>
			                </td>
		                </tr>
		                </c:forEach>
	                </table>
  	</td>
            <td class="mainrightborder"></td>
        </tr>
    </table>
    <%@ include file="foot.jsp"%>
  	
      	 <script>
    $(function(){ 
    	$(".del").click(function(){
    		var id = $(this).attr("id");
		    $.messager.confirm("系统提示", "您确认要注销此社团吗？", function(r) {
				if (r) {
					$.post("${basePath}teacher/del.do", {
						id:id
					}, function(result) {
						if (result.success) {
								 $.messager.alert("系统提示", result.mgf, "info", function () {
									window.location.reload();
						        }); 
						} else {
							$.messager.alert("系统提示", result.mgf);
						}
					}, "json");
				}
			});
    	});
    })
    </script>
  </body>
</html>
