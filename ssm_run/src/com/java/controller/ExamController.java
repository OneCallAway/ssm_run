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
@RequestMapping("/exam")
public class ExamController {
	private ExamService examService;

	public ExamService getExamService() {
		return examService;
	}

	@Autowired
	public void setExamService(ExamService examService) {
		this.examService = examService;
	}
	@Autowired
	private NewsService newsService;
	@Autowired
	private RadioService radioService;
	@Autowired
	private PanDuanService panduanService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private ExamRadioService examradioService;
	@Autowired
	private ExamPanDuanService exampanduanService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	@SuppressWarnings("finally")
	@RequestMapping("/addpage")
	public String addpage() {
		request.setAttribute("news", newsService.Get(null));
		return "teacher/addexam";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(Exam nt,int newsId,int rnum,int rscore,int pdnum,int pdscore) {
		Teacher t=(Teacher)request.getSession().getAttribute("teacher");
		nt.setTeacherId(t.getId());
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			int count = examService.Add(nt);
			if(count>0)
			{
				//单选题组卷
				Pages p=new Pages();
				p.setOrder("RAND()");//设置随机
				p.setPagesize(rnum);
				Radio r=new Radio();
				r.setPage(p);
				r.setGrade(nt.getGrade());
				r.setNewsId(newsId);
				List<Radio> rs=radioService.Get(r);
				for(int i=0;i<rs.size();i++)
				{
					ExamRadio er=new ExamRadio();
					er.setExamId(nt.getId());
					er.setRadioId(rs.get(i).getId());
					er.setScore(rscore);
					examradioService.Add(er);
				}
				

				p.setPagesize(pdnum);
				PanDuan pd=new PanDuan();
				pd.setPage(p);
				pd.setGrade(nt.getGrade());
				pd.setNewsId(newsId);
				List<PanDuan> pds=panduanService.Get(pd);
				for(int i=0;i<pds.size();i++)
				{
					ExamPanDuan er=new ExamPanDuan();
					er.setExamId(nt.getId());
					er.setPanduanId(pds.get(i).getId());
					er.setScore(rscore);
					exampanduanService.Add(er);
				}
				map.put("mgf", "操作成功");
				map.put("success", true);
			}
			else
			{
				map.put("mgf", "操作失败");
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

	@RequestMapping("/teacher_list")
	public String Get(Exam exam) {
		Teacher t=(Teacher)request.getSession().getAttribute("teacher");
		exam.setTeacherId(t.getId());
		try {
			List<Exam> list = examService.Get(exam);
			System.out.println(list);
			request.setAttribute("list", list);
			return "teacher/exam";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	///查看学生有没有考过试了
	@RequestMapping("/client_getis")
	public String client_getis(int id) {
		Client c=(Client)request.getSession().getAttribute("client");
		Score s=new Score();
		s.setClientId(c.getId());
		s.setExamId(id);
		if(scoreService.GetCount(s)>0)
			return Util.SetMap("您已考过本试卷，不能二考", false, response);
		return Util.SetMap("", true, response);
		
	}
	
	@RequestMapping("/client_list")
	public String client_list(Exam exam) {
		try {
			List<Exam> list = examService.Get(exam);
			System.out.println(list);
			request.setAttribute("list", list);
			return "client/exam";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/client_show")
	public String client_show(int  id) {
		try {
			Exam exam = examService.GetByID(id);
			ExamRadio r=new ExamRadio();
			r.setExamId(id);
			ExamPanDuan pd=new ExamPanDuan();
			pd.setExamId(id);
			List<ExamRadio> ers=examradioService.Get(r);
			for(int i=0;i<ers.size();i++)
			{
				Radio radio=radioService.GetByID(ers.get(i).getRadioId());
				ers.get(i).setRadio(radio);
			}
			
			List<ExamPanDuan> pds=exampanduanService.Get(pd);
			for(int i=0;i<pds.size();i++)
			{
				PanDuan panduan=panduanService.GetByID(pds.get(i).getPanduanId());
				pds.get(i).setPanduan(panduan);
			}
			
			request.setAttribute("radio", ers);
			request.setAttribute("panduan", pds);
			request.setAttribute("exam", exam);
			return "client/examshow";
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
			int r = examService.Del(id);
			
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