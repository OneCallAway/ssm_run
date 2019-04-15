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
@RequestMapping("/score")
public class ScoreController {
	private ScoreService scoreService;

	public ScoreService getScoreService() {
		return scoreService;
	}

	@Autowired
	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}
	@Autowired
	private ClientService clientService;
	@Autowired
	private RadioService radioService;
	@Autowired
	private PanDuanService panduanService;
	@Autowired
	private ExamRadioService examradioService;
	@Autowired
	private ExamPanDuanService exampanduanService;
	@Autowired
	private ExamService examService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	

	@RequestMapping("/client_list")
	public String Get(Score er) {
		Client c=(Client)request.getSession().getAttribute("client");
		er.setClientId(c.getId());
		try {
			List<Score> list = scoreService.Get(er);
			for(int i=0;i<list.size();i++)
			{
				list.get(i).setExam(examService.GetByID(list.get(i).getExamId()));
				
			}
			request.setAttribute("list", list);
			return "client/score";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/teacher_list")
	public String teacher_list(Score er) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(100);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		p.setOrder("score desc");
		er.setPage(p);
		
		try {
			List<Score> list = scoreService.Get(er);
			for(int i=0;i<list.size();i++)
			{
				list.get(i).setExam(examService.GetByID(list.get(i).getExamId()));
				list.get(i).setClient(clientService.GetByID(list.get(i).getClientId()));
			}
			request.setAttribute("list", list);
			return "teacher/score";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/add")
	public String add(int eid) {
		Client c=(Client)request.getSession().getAttribute("client");
		Score s=new Score();
		s.setExamId(eid);
		s.setClientId(c.getId());
		if(scoreService.Add(s)==0)
			return Util.SetMap("提交失败", false, response);
		
		try {
			Exam exam = examService.GetByID(eid);
			ExamRadio r=new ExamRadio();
			r.setExamId(eid);
			ExamPanDuan pd=new ExamPanDuan();
			pd.setExamId(eid);
			List<ExamRadio> ers=examradioService.Get(r);
			
			int sum=0;
			for(int i=0;i<ers.size();i++)
			{
				String answer=request.getParameter("r_"+ers.get(i).getId().toString());
				Radio radio=radioService.GetByID(ers.get(i).getRadioId());
				sum+=answer.toLowerCase().equals(radio.getRight().toLowerCase())?ers.get(i).getScore():0;
			}
			
			List<ExamPanDuan> pds=exampanduanService.Get(pd);
			
			
			for(int i=0;i<pds.size();i++)
			{
				String answer=request.getParameter("p_"+pds.get(i).getId().toString());
				PanDuan panduan=panduanService.GetByID(pds.get(i).getPanduanId());
				sum+=answer.toLowerCase().equals(panduan.getRight().toLowerCase())?pds.get(i).getScore():0;
			}
			s.setScore(sum);
			if(scoreService.EditScore(s)>0)
				return Util.SetMap("提交成功，您成功考了"+sum+"分", true, response);
			else
				return Util.SetMap("操作失败", false, response);
		} catch (Exception e) {
			return Util.SetMap(e.getMessage(), false, response);
		} 
	}
	
	
	@SuppressWarnings("finally")
	@RequestMapping(value="/del", method = RequestMethod.POST)
	public String Del(@RequestParam(value = "id") int id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println("================================");
			System.out.println(id);
			int r = scoreService.Del(id);
			
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