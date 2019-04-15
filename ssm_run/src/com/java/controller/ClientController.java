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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.java.model.*;
import com.java.service.*;
import com.java.utils.CommonUtils;
import com.java.utils.PageList;
import com.java.utils.ResponseUtil;
import com.sun.corba.se.spi.protocol.RequestDispatcherRegistry;;

@Controller
@RequestMapping("/client")
public class ClientController {
	private ClientService clientService;

	public ClientService getClientService() {
		return clientService;
	}

	@Autowired
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

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
			model.addAttribute("client", clientService.GetByID(id));
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "teacher/addclient";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/pwd")
	public String pwd() {
		
		Client c=(Client)request.getSession().getAttribute("client");
		request.setAttribute("client", c);
		return "client/pwd";
	}
	
	
	
	@SuppressWarnings("finally")
	@RequestMapping("/client_editpage")
	public String EditForm() {
		Client c=(Client)request.getSession().getAttribute("client");
		request.setAttribute("client", c);
		return "client/edit";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/editfilepage")
	public String editfilepage() {
		Client c=(Client)request.getSession().getAttribute("client");
		request.setAttribute("client", c);
		return "editfile";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/add")
	public String Add(Client c) {
		try {
			Client client=clientService.Login(c.getLogin());
//			if(client!=null)
//				return Util.SetMap("该学号已存在", false, response);
			String id=request.getParameter("id").trim().length()==0?"0":request.getParameter("id");
			c.setId(Integer.parseInt(id));
			
			if(c.getFile()=="")
				c.setFile("images/noimg.jpg");
			int r = 0;
			if(c.getId()==0)
				r = clientService.Add(c);
			else
				r=clientService.Edit(c);
			if(r>0)
				return Util.SetMap("操作成功", true, response);
			else
				return Util.SetMap("操作失败", false, response);
		} catch (Exception e) {
			return Util.SetMap(e.getMessage(), true, response);
		} 
	}
	
	@SuppressWarnings("finally")
	@RequestMapping("/edit")
	public String Edit(Client client) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Client c=(Client)request.getSession().getAttribute("client");
			client.setId(c.getId());
			client.setLogin(c.getLogin());
			client.setPwd(c.getPwd());
			int r=clientService.Edit(client);
			if(r>0)
			{
				map.put("mgf", "更新成功");
				map.put("success", true);
				request.getSession().removeAttribute("client");
				request.getSession().setAttribute("client", client);
			}
			else
			{
				map.put("mgf", "更新失败");
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
	
	@SuppressWarnings("finally")
	@RequestMapping("/editpass")
	public String EditPass() {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Client c=(Client)request.getSession().getAttribute("client");
			c.setPwd(request.getParameter("pwd"));
			int r=clientService.EditPass(c);
			if(r>0)
			{
				map.put("mgf", "密码修改成功，下次请用新密码登陆");
				map.put("success", true);
				request.getSession().setAttribute("client", c);
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
	
	@SuppressWarnings("finally")
	@RequestMapping("/editfile")
	public String editfile() {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Client c=(Client)request.getSession().getAttribute("client");
			c.setFile(request.getParameter("img"));
			int r=clientService.EditFile(c);
			if(r>0)
				return Util.SetMap("上传成功", true, response);
			else
				return Util.SetMap("上传失败", false, response);
		} catch (Exception e) {
			return Util.SetMap(e.getMessage(), false, response);
		} 
	}

	@RequestMapping("/admin_list")
	public String Get(Client c) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		c.setPage(p);
		try {
			List<Client> list = clientService.Get(c);
			System.out.println(list);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request,"client/admin_list.do", clientService.GetCount(c), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "admin/client";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/teacher_list")
	public String teacher_list(Client c) {
		//分页参数设置
		Pages p=new Pages();
		p.setPagesize(10);//每页显示数量 
		int startindex=request.getParameter("startindex")==null?0:Integer.parseInt(request.getParameter("startindex"));//起始页，默认从第1页开始读
		p.setStartindex(startindex);
		c.setPage(p);
		try {
			List<Client> list = clientService.Get(c);
			System.out.println(list);
			request.setAttribute("list", list);
			//分页
			request.setAttribute("pages", PageList.Page(request,"client/admin_list.do", clientService.GetCount(c), 
					p.getPagesize(), p.getStartindex(),request.getQueryString()));
			return "teacher/client";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@RequestMapping("/showmes")
	public String GetByID() {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			Client c = clientService.GetByID(id);
			request.setAttribute("client", c);
			return "reg";
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping("/login")
	public String Login(Client client) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Client a = clientService.Login(client.getLogin());
			
			if(a==null)
			{
				map.put("mgf", "用户不存在");
				map.put("success", false);
			}
			else if(!a.getPwd().equals(client.getPwd()))
			{
				map.put("mgf", "密码错误");
				map.put("success", false);
			}
			else
			{
				request.getSession().setAttribute("client", a);
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
	@RequestMapping(value="/client_del", method = RequestMethod.POST)
	public String Del(@RequestParam(value = "id") int id) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println("================================");
			System.out.println(id);
			int r = clientService.Del(id);
			
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
			request.getSession().removeAttribute("client");
			return "login";

	}
}