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
@RequestMapping("/radio")
public class RadioController {
	private RadioService radioService;

	public RadioService getRadioService() {
		return radioService;
	}

	@Autowired
	public void setRadioService(RadioService radioService) {
		this.radioService = radioService;
	}
	
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
			model.addAttribute("radio", radioService.GetByID(id));
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "teacher/addradio";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/add")
	public String add(Radio n) {
		try {
			String id=request.getParameter("id").trim().length()==0?"0":request.getParameter("id");
			n.setId(Integer.parseInt(id));
			int count=0;
			if(n.getId()==0)
				count = radioService.Add(n);
			else
				count=radioService.Edit(n);
			if(count>0)
				return Util.SetMap("操作成功", true, response);
			else
				return Util.SetMap("操作失败", false, response);
		} catch (Exception e) {
			return Util.SetMap(e.getMessage(), false, response);
		} 
	}

	@RequestMapping("/teacher_list")
	public String Get(Radio n) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		try {
			n.setPage(p);
			List<Radio> list = radioService.Get(n);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request,"radio/teacher_list.do", radioService.GetCount(n), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "teacher/radio";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value="/del", method = RequestMethod.POST)
	public String Del(@RequestParam(value = "id") int id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
//		Radio n=radioService.GetByID(id);
//		if(n.getState()>0)
//			return Util.SetMap("该活动已被禁止删除", false, response);
		try {
			System.out.println("================================");
			System.out.println(id);
			//int id=Integer.parseInt(request.getParameter("id"));
			
			int r = radioService.Del(id);
			
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