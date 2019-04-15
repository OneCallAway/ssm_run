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
  		
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
			  <legend>${exam.title }</legend>
			</fieldset>
			
			<form class="layui-form" id="sqlform" method="post">
			<input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="submit" value="提交试卷"></input>
			<blockquote class="layui-elem-quote" style="margin-top:30px">单选题</blockquote>
			<div class="layui-collapse" lay-filter="test">
			<c:forEach var="list"  items="${radio}">
			  <div class="layui-colla-item">
			    <h2 class="layui-colla-title">${list.radio.title }</h2>
			    <div class="layui-colla-content layui-show">
			    
			      <p><input name="r_${list.id }" value="A" title="A:${list.radio.a }"  type="radio"></p>
			      <p><input name="r_${list.id }" value="B" title="B:${list.radio.b }"  type="radio"></p>
			      <p><input name="r_${list.id }" value="C" title="C:${list.radio.c }"  type="radio"></p>
			      <p><input name="r_${list.id }" value="D" title="D:${list.radio.d }"  type="radio"></p>
			    </div>
			  </div>
			</c:forEach>
			</div>
			
			<blockquote class="layui-elem-quote" style="margin-top:30px">判断题</blockquote>
			<div class="layui-collapse" lay-filter="test">
			<c:forEach var="list"  items="${panduan}">
			  <div class="layui-colla-item">
			    <h2 class="layui-colla-title">${list.panduan.title }</h2>
			    <div class="layui-colla-content layui-show">
			      <p><input name="p_${list.id }" value="A" title="正确"  type="radio"></p>
			      <p><input name="p_${list.id }" value="B" title="错误"  type="radio"></p>
			    </div>
			  </div>
			</c:forEach>
			</div>
		</form>

  	</td>
            <td class="mainrightborder"></td>
        </tr>
    </table>
    <%@ include file="foot.jsp"%>
  	
  	<script>
  	layui.use(['form', 'layedit', 'laydate','layer','upload','element'], function(){
  	   var form = layui.form
  	   ,layedit = layui.layedit
  	   ,laydate = layui.laydate
  	   ,layer=layui.layer
  	   ,upload = layui.upload
  	   ,element=layui.element;
  	   
  	//监听提交
  	   form.on('submit(demo1)', function(data){
  		 $.messager.confirm("系统提示", "您确认要提交试卷吗？", function(r) {
				if (r) {
	  		       $("#sqlform").form("submit",{
	  					url : "${basePath}score/add.do?eid=${exam.id}",
	  					onSubmit : function() { },
	  					success : function(result) {
	  						var result = eval('(' + result + ')');
	  						if (result.success) {
	  								$.messager.alert("系统提示", result.mgf, "info", function () {
	  									location.href='${basePath}score/client_list.do';
	  						        }); 
	  							} else {
	  								$.messager.alert("系统提示", result.mgf);
	  							}
	  					}
	  				});  
				}
  		 })
  	  		 return false;
  	  	}) 
  	  	
	});
	</script>
  	
  </body>
</html>
