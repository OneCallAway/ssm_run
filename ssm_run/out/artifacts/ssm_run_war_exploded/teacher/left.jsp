<%@ page language="java" import="java.util.*,com.java.model.*" pageEncoding="utf-8"%>


    
    <ul class="menuul">
                    <li class="left_par"><b>成绩查看</b></li>
                    <li class="left_chi"><a href="${basePath_top }score/teacher_list.do?state=0">成绩查看</a></li>
                   
                   <li class="left_par"><b>学生管理</b></li>
                    <li class="left_chi"><a href="${basePath_top }client/addpage.do">添加学生</a></li>
                   <li class="left_chi"><a href="${basePath_top }client/teacher_list.do">学生管理</a></li>
                   
                   <li class="left_par"><b>题库管理</b></li>
                    <li class="left_chi"><a href="${basePath_top }news/addpage.do">添加试题</a></li>
                   <li class="left_chi"><a href="${basePath_top }news/teacher_list.do">试题管理</a></li>
                   
                   <li class="left_par"><b>试卷管理</b></li>
                    <li class="left_chi"><a href="${basePath_top }exam/addpage.do">生成试卷</a></li>
                   <li class="left_chi"><a href="${basePath_top }exam/teacher_list.do">试卷管理</a></li>
                   
                   <li class="left_par"><b>系统设置</b></li>
                    <li class="left_chi"><a href="${basePath_top }teacher/teacher_editpage.do">更新信息</a></li>
                   <li class="left_chi"><a href="${basePath_top }teacher/pwdpage.do">修改密码</a></li>
                   <li class="left_chi"><a href="${basePath_top }admin/exit.do">退出</a></li>
                   
                </ul>
