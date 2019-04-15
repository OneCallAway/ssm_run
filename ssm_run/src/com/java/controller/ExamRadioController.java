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
import com.java.utils.ResponseUtil;;

@Controller
@RequestMapping("/examradio")
public class ExamRadioController {
	private ExamRadioService examradioService;

	public ExamRadioService getExamRadioService() {
		return examradioService;
	}

	@Autowired
	public void setExamRadioService(ExamRadioService examradioService) {
		this.examradioService = examradioService;
	}

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	

	@RequestMapping("/teacher_list")
	public String Get(ExamRadio er) {
		try {
			List<ExamRadio> list = examradioService.Get(er);
			System.out.println(list);
			request.setAttribute("list", list);
			return "teacher/examradio";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	@SuppressWarnings("finally")
	@RequestMapping(value="/del", method = RequestMethod.POST)
	public String Del(@RequestParam(value = "id") int id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println("================================");
			System.out.println(id);
			int r = examradioService.Del(id);
			
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