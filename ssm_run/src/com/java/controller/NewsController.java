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
import com.java.utils.ResponseUtil;
import com.java.utils.StringUtil;;

@Controller
@RequestMapping("/news")
public class NewsController {
	private NewsService newsService;

	public NewsService getNewsService() {
		return newsService;
	}

	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	@Autowired
	private NewsTypeService newstypeService;
	@Autowired
	private TeacherService teacherService;
	
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
			model.addAttribute("news", newsService.GetByID(id));
			List<NewsType> nts= newstypeService.Get();
			model.addAttribute("newstype",nts);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "teacher/addnews";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/add")
	public String add(News n) {
		Teacher t=(Teacher)request.getSession().getAttribute("teacher");
		try {
			String id=request.getParameter("id").trim().length()==0?"0":request.getParameter("id");
			n.setId(Integer.parseInt(id));
			if(Integer.parseInt(id)==0&&n.getImg().trim().length()==0)
				n.setImg("images/no.jpg");
			//n.setContent(request.getParameter("content"));
			n.setTeacherId(t.getId());
			int count=0;
			if(n.getId()==0)
				count = newsService.Add(n);
			else
				count=newsService.Edit(n);
			if(count>0)
				return Util.SetMap("操作成功", true, response);
			else
				return Util.SetMap("操作失败", false, response);
		} catch (Exception e) {
			return Util.SetMap(e.getMessage(), false, response);
		} 
	}

	@RequestMapping("/teacher_list")
	public String Get(News n) {
		Teacher t=(Teacher)request.getSession().getAttribute("teacher");
		n.setTeacherId(n.getId());
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		try {
			n.setPage(p);
			List<News> list = newsService.Get(n);
			for(int i=0;i<list.size();i++)
			{
				list.get(i).setNewstype(newstypeService.GetByID(list.get(i).getNewstypeId()));
				
			}
			System.out.println(list);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request,"news/teacher_list.do", newsService.GetCount(n), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "teacher/news";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/admin_list")
	public String admin_list(News n) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		try {
			n.setPage(p);
			List<News> list = newsService.Get(n);
			for(int i=0;i<list.size();i++)
				list.get(i).setNewstype(newstypeService.GetByID(list.get(i).getNewstypeId()));
			System.out.println(list);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request,"news/admin_list.do", newsService.GetCount(n), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "admin/news";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/state")
	public String state(News c) {
		int r=newsService.EditState(c);
		if(r>0)
			return Util.SetMap("操作成功", true, response);
		else
			return Util.SetMap("操作失败", false, response);
	}
	
	@RequestMapping("/web_list")
	public String Get2(News n) {

		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		n.setState(1);
		n.setPage(p);
		try {
			List<News> list = newsService.Get(n);
			for(int i=0;i<list.size();i++)
			{
				String content=StringUtil.CleanHTML(list.get(i).getContent());
				list.get(i).setContent(content);
				list.get(i).setNewstype(newstypeService.GetByID(list.get(i).getNewstypeId()));
				list.get(i).setTeacher(teacherService.GetByID(list.get(i).getTeacherId()));
			}
			System.out.println(list);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request,"news/web_list.do", newsService.GetCount(n), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			System.out.println(request.getQueryString());
			return "news";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/show")
	public String GetByID(int id) {
		try {
			News n = newsService.GetByID(id);
			request.setAttribute("news", n);
			return "newsshow";
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	@SuppressWarnings("finally")
	@RequestMapping(value="/del", method = RequestMethod.POST)
	public String Del(@RequestParam(value = "id") int id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
//		News n=newsService.GetByID(id);
//		if(n.getState()>0)
//			return Util.SetMap("该活动已被禁止删除", false, response);
		try {
			System.out.println("================================");
			System.out.println(id);
			//int id=Integer.parseInt(request.getParameter("id"));
			
			int r = newsService.Del(id);
			
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
	
	
}