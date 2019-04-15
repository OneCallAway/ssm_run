<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="<%=basePath %>js/bootstrap/css/bootstrap.min.css" />
<link href="<%=basePath %>css/login.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath %>js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script src="<%=basePath %>js/layer/layer.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layui/layui.js"  type="text/javascript"></script>
<link rel="stylesheet" href="<%=basePath %>js/layui/css/layui.css" />

<title>登录页面</title>
</head>
<body>
	<form class="layui-form" id="sqlform" method="post">
	<div id="loginborder">
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td style="height:35px"><input type="text" name="login"  lay-verify="required" /></td>
            </tr>
            <tr>
                <td style="height:35px"><input type="password" name="pwd"  lay-verify="required" /></td>
            </tr>
            <tr>
                <td style="height:35px">
					<input name="author" value="${basePath}admin/login.do" title="管理" checked="" type="radio">
      				<input name="author" value="${basePath}teacher/login.do" title="教师" type="radio">
      				<input name="author" value="${basePath}client/login.do" title="学生" type="radio">
				</td>
            </tr>
        </table>
        <input lay-submit  lay-filter="demo1"  type="submit" value="" class="btn" />
    </div>
    
    
		
	</form>

	<script type="text/javascript">
//Demo
layui.use(['form', 'layedit', 'laydate','layer'], function(){
     var form = layui.form
     ,layedit = layui.layedit
     ,laydate = layui.laydate
     ,layer=layui.layer;
   
   //监听提交
     form.on('submit(demo1)', function(data){
    	 
	       $("#sqlform").form("submit",{
				url : $("input[name='author']:checked").val(),
				onSubmit : function() { },
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success) {
							if("${basePath}admin/login.do"==$("input[name='author']:checked").val())
								location.href='${basePath}teacher/admin_list.do';
							else if("${basePath}teacher/login.do"==$("input[name='author']:checked").val())
								location.href='${basePath}news/teacher_list.do';
							else
								location.href='${basePath}introduce/client_show.do?id=1';
						} else {
							layer.alert(result.mgf);
						}
				}
			});  
    		 return false;
    	}) 
});

 
</script>
</body>
</html>