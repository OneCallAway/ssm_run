package com.java.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.model.*;
import com.java.service.*;
import com.java.utils.CommonUtils;

@Controller
/*@RequestMapping("/line")*/
public class indexController {
	@Autowired
	private IntroduceService introduceService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private NewsTypeService newstypeService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private TeacherTypeService teachertypeService;
	@Autowired
	private HttpServletRequest request;

	/**
	 * 用户首页
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(Model model){
		Pages p=new Pages();
		p.setPagesize(6);
		Teacher t=new Teacher();
		t.setPage(p);

		List<Teacher> teacher = teacherService.Get(t);
		for(int i=0;i<teacher.size();i++)
		{
			TeacherType tt=teachertypeService.GetByID(teacher.get(i).getTeachertypeId());
			teacher.get(i).setTeachertype(tt);
			String title=teacher.get(i).getName();
			title=title.length()>17?title.substring(0, 17)+"...":title;
			teacher.get(i).setName(title);
		}
		Introduce intr = introduceService.GetByID(1);
		model.addAttribute("teacher", teacher);
		model.addAttribute("intr", intr);
		request.getSession().setAttribute("newstype", newstypeService.Get());
		request.getSession().setAttribute("thingtype", teachertypeService.Get());
		return "index";
		
	}
}