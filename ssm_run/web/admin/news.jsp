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
			               <td>标题</td>
			                <td>类别</td>
			                <td>发布日期</td>
			                <%if(request.getParameter("state").equals("0")){ %>
			                <td>状态</td>
			                
			                <td>操作</td>
			                <%} %>
		                </tr>
                		<c:forEach var="list"  items="${list}">
		                <tr class="rows">
			                <td>${list.title}</td>
			                <td>${list.newstype.type}</td>
			                
			                <td><fmt:formatDate value="${list.intime }" pattern="yyyy-MM-dd"/></td>
			                <%if(request.getParameter("state").equals("0")){ %>
			                <td>${list.statetext}</td>
			                
			                <td>
			                		<a href="javascript:;;" id="${list.id }_1" class="state">通过</a>
				                	<a href="javascript:;;" id="${list.id }_2" class="state">拒绝</a>
			                	</td>
			                <%} %>
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
  	
    <script>
    $(function(){ 
    	$(".state").click(function(){
    		var iditem = $(this).attr("id").split("_");
		    $.messager.confirm("系统提示", "您确认要执行该操作吗？", function(r) {
				if (r) {
					$.post("${basePath}news/state.do", {
						id:iditem[0],state:iditem[1]
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
