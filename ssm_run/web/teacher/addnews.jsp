<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
            
  		<form class="layui-form" id="sqlform" method="post">
  		<table cellpadding="0" cellspacing="0" id="addtable"> 
	  	            <tr>
	  		            <td class="left">标题：</td>
	  		            <td class="right"><input type="text" lay-verify="required" name="title" class="layui-input" value="${news.title}" /></td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">分类：</td>
	  		            <td class="right">
							<select name="newstypeId" lay-filter="aihao">
							<c:forEach var="list"  items="${newstype}">
						        <option value="${list.id }">${list.type }</option>
						        </c:forEach>
						      </select>
						</td>
	  	            </tr>
	  	            <tr>
	  		            <td class="left">内容：</td>
	  		            <td class="right"><textarea id="content" name="content" lay-verify="content" style="width:98%; height:300px">${news.content}</textarea></td>
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
   
   
   layedit.set({
	   uploadImage: {
	   url: '${basePath}upload.do' //接口url
	   ,type: 'post' //默认post
	   }
	   });
   var editIndex = layedit.build('content'); 
   //同步编辑器，否则获取不到更新的内容
   form.verify({
	   content: function(value){
		      layedit.sync(editIndex);
		    }
	})
	
 //监听提交
   form.on('submit(demo1)', function(data){
	       $("#sqlform").form("submit",{
				url : "${basePath}news/add.do?id=${news.id}",
				onSubmit : function() { },
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success) {
							$.messager.alert("系统提示", result.mgf, "info", function () {
								location.href='${basePath}news/teacher_list.do';
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
