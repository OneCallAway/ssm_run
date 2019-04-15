<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
	pageContext.setAttribute("newsId", request.getParameter("newsId"));
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
            
  		<form class="layui-form" id="sqlform" method="post">
  		<table cellpadding="0" cellspacing="0" id="addtable"> 
	  	            <tr>
	  		            <td class="left">标题：</td>
	  		            <td class="right"><input type="text" lay-verify="required" name="title" class="layui-input" value="${panduan.title}" /></td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">正确答案</td>
	  		            <td class="right">
							<input name="right" value="A" title="正确" checked="" type="radio">
							<input name="right" value="B" title="错误" type="radio">
						</td>
	  	            </tr>
	  	            
	  	            <tr>
	  		            <td class="left">难度</td>
	  		            <td class="right">
							<select name="grade" lay-filter="aihao">
						        <option value="0">特级</option>
						        <option value="1">高</option>
						        <option value="2" selected="">中</option>
						        <option value="3">低</option>
						      </select>
						</td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">&nbsp;</td>
	  		            <td class="right"><input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="submit" value="提交"></input></td>
	  	            </tr>
	  	        </table>
	  	  </form>
  	</td>
            <td class="mainrightborder"></td>
        </tr>
    </table>
    <%@ include file="foot.jsp"%>


<script type="text/javascript">
//Demo
layui.use(['form', 'layedit', 'laydate','layer','upload'], function(){
   var form = layui.form
   ,layedit = layui.layedit
   ,laydate = layui.laydate
   ,layer=layui.layer
   ,upload = layui.upload;;

	
 //监听提交
   form.on('submit(demo1)', function(data){
	       $("#sqlform").form("submit",{
				url : "${basePath}panduan/add.do?id=${panduan.id}&&newsId=${newsId}",
				onSubmit : function() { },
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success) {
							$.messager.alert("系统提示", result.mgf, "info", function () {
								location.href='${basePath}panduan/teacher_list.do?newsId=${newsId}';
					        }); 
						} else {
							$.messager.alert("系统提示", result.mgf);
						}
				}
			});  
  		 return false;
  	}) 
});
	
</script>

    
  </body>
</html>
