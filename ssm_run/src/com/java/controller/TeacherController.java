package com.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.*;
import com.java.service.*;
import com.java.utils.CommonUtils;
import com.java.utils.PageList;
import com.java.utils.ResponseUtil;;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	private TeacherService teacherService;

	public TeacherService getTeacherService() {
		return teacherService;
	}

	@Autowired
	public void setThingService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	@Autowired
	private TeacherTypeService teachertypeService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	@SuppressWarnings("finally")
	@RequestMapping("/addpage")
	public String add(Model model) {
		try {
			int id=0;
			if(request.getParameter("id")!=null)
				id=Integer.parseInt(request.getParameter("id"));
			model.addAttribute("teacher", teacherService.GetByID(id));
			model.addAttribute("teachertype",teachertypeService.Get());
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/addteacher";
	}
//	
//	@RequestMapping("/editpage")
//	public String editpage(Model m,int id) {
//		try {
//			Teacher teacher=teacherService.GetByID(id);
//			m.addAttribute("teacher",teacher);
//			m.addAttribute("teachertype",teachertypeService.Get());
//			return "admin/addteacher";
//		} catch (Exception e) {
//			return null;
//		}
//	}
	
	@RequestMapping("/teacher_editpage")
	public String teacher_editpage(Model m) {
		try {
			Teacher teacher=(Teacher)request.getSession().getAttribute("teacher");
			m.addAttribute("teacher",teacher);
			m.addAttribute("teachertype",teachertypeService.Get());
			return "teacher/edit";
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping("/pwdpage")
	public String editpwd(Model m) {
		try {
			Teacher teacher=(Teacher)request.getSession().getAttribute("teacher");
			m.addAttribute("teacher",teacher);
			return "teacher/pwd";
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping("/show")
	public String show(Model m) {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			Teacher r = teacherService.GetByID(id);

				TeacherType tt=teachertypeService.GetByID(r.getTeachertypeId());
				r.setTeachertype(tt);
			m.addAttribute("teacher",r);
			return "teachershow";
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/pwd")
	public String EditPass() {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Teacher t=(Teacher)request.getSession().getAttribute("teacher");
			t.setPwd(request.getParameter("pwd"));
			int r=teacherService.EditPass(t);
			if(r>0)
			{
				map.put("mgf", "密码修改成功，下次请用新密码登陆");
				map.put("success", true);
				request.getSession().setAttribute("teacher", t);
			}
			else
			{
				map.put("mgf", "密码修改失败");
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("mgf", "错误："+e.getMessage());
			map.put("success", false);
		} 
		String result = new JSONObject(map).toString();
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/login")
	public String Login(Teacher teacher) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Teacher t = teacherService.Login(teacher);
			
			if(t==null)
			{
				map.put("mgf", "用户不存在");
				map.put("success", false);
			}
			else if(!t.getPwd().equals(teacher.getPwd()))
			{
				map.put("mgf", "密码错误");
				map.put("success", false);
			}
			else
			{
				request.getSession().setAttribute("teacher", t);
				map.put("mgf", "验证通过!");
				map.put("success", true);
			}
		} catch (Exception e) {
			map.put("mgf", "错误："+e.getMessage());
			map.put("success", false);
		} 
		String result = new JSONObject(map).toString();
		ResponseUtil.write(response, result);
		return null;
	}

	
	@SuppressWarnings("finally")
	@RequestMapping("/add")
	public String Add(Teacher t) {
		
		try {
			Teacher teacher=teacherService.Login(t);
			if(teacher!=null&&t.getId()==0)
				return Util.SetMap("该教师号已存在", false, response);
			String id=request.getParameter("id").trim().length()==0?"0":request.getParameter("id");
			t.setId(Integer.parseInt(id));
			
			if(t.getImg()=="")
				t.setImg("images/noimg.jpg");
			int r = 0;
			if(t.getId()==0)
				r = teacherService.Add(t);
			else
				r=teacherService.Edit(t);
			if(r>0)
				return Util.SetMap("操作成功", true, response);
			else
				return Util.SetMap("操作失败", false, response);
		} catch (Exception e) {
			return Util.SetMap(e.getMessage(), false, response);
		} 
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/edit")
	public String edit(Teacher t) {
		
		try {
			Teacher teacher=(Teacher)request.getSession().getAttribute("teacher");
			t.setId(teacher.getId());
			
			int r = teacherService.Edit(t);
			if(r>0)
			{
				request.getSession().setAttribute("teacher", t);
				return Util.SetMap("修改成功", true, response);
			}
			else
				return Util.SetMap("修改失败", false, response);
		} catch (Exception e) {
			return Util.SetMap(e.getMessage(), false, response);
		} 
	}

	@RequestMapping("/admin_list")
	public String Get(Teacher h) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		h.setPage(p);
		try {
			List<Teacher> list = teacherService.Get(h);
			for(int i=0;i<list.size();i++)
			{
				TeacherType tt=teachertypeService.GetByID(list.get(i).getTeachertypeId());
				list.get(i).setTeachertype(tt);
			}
			System.out.println(list);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request,"teacher/admin_list.do", teacherService.GetCount(h), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "admin/teacher";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/web_list")
	public String web_list(Teacher h) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		h.setPage(p);
		try {
			List<Teacher> list = teacherService.Get(h);
			for(int i=0;i<list.size();i++)
			{
				TeacherType tt=teachertypeService.GetByID(list.get(i).getTeachertypeId());
				list.get(i).setTeachertype(tt);

			}
			System.out.println(list);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request,"teacher/web_list.do", teacherService.GetCount(h), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "teacher";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/del")
	public String Del(int id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println("================================");
			System.out.println(id);
			//int id=Integer.parseInt(request.getParameter("id"));
			
			int r = teacherService.Del(id);
			
			if(r>0)
			{
				map.put("mgf", "删除成功");
				map.put("success", true);
			}
			else
			{
				map.put("mgf", "删除失败");
				map.put("success", false);
			}
		} catch (Exception e) {
			map.put("mgf", "错误："+e.getMessage());
			map.put("success", false);
		} 
		String result = new JSONObject(map).toString();
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/exit")
	public String Exit() {
		
		request.getSession().removeAttribute("teacher");
			
		return "login";
	}
	
}