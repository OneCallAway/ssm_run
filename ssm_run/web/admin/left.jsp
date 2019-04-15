<%@ page language="java" import="java.util.*,com.java.model.*" pageEncoding="utf-8"%>


    
    <ul class="menuul">
                    <li class="left_par"><b>教师管理</b></li>
                    <li class="left_chi"><a href="${ basePath_top }teacher/addpage.do">添加教师</a></li>
                    <li class="left_chi"><a href="${ basePath_top }teacher/admin_list.do">教师查看</a></li>
                   
                   <li class="left_par"><b>学生管理</b></li>
                   <li class="left_chi"><a href="${ basePath_top }client/admin_list.do">学生查看</a></li>
                   
                   <li class="left_par"><b>职称管理</b></li>
                    <li class="left_chi"><a href="${ basePath_top }teachertype/addpage.do">添加职称</a></li>
                   <li class="left_chi"><a href="${ basePath_top }teachertype/admin_list.do">职称管理</a></li>
                   
                   <li class="left_par"><b>课程管理</b></li>
                    <li class="left_chi"><a href="${ basePath_top }newstype/addpage.do">添加课程</a></li>
                   <li class="left_chi"><a href="${ basePath_top }newstype/admin_list.do">课程管理</a></li>
					
                   
                   <li class="left_par"><b>系统管理</b></li>
                    <li class="left_chi"><a href="${ basePath_top }introduce/introduce_list.do">网站信息</a></li>
                   <li class="left_chi"><a href="${ basePath_top }admin/exit.do">退出</a></li>
                   
                </ul>

