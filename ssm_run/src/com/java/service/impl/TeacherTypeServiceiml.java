package com.java.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.TeacherTypeService;
@Service("TeacherTypeService")
public class TeacherTypeServiceiml implements TeacherTypeService {

	private TeacherTypeMapper teachertypeMapper;

	public TeacherTypeMapper getAddMapper() {
		return teachertypeMapper;
	}
	@Autowired
	public void setAddMapper(TeacherTypeMapper teachertypeMapper) {
		this.teachertypeMapper = teachertypeMapper;
	}
	
	@Override    
	public int Edit(TeacherType teachertype) {
		return teachertypeMapper.Edit(teachertype);
	}
	
	@Override
	public TeacherType GetByID(int id) {
		return teachertypeMapper.GetByID(id);
	}
	
	@Override
	public int Add(TeacherType teachertype) {
		return teachertypeMapper.Add(teachertype);
	}
	
	@Override
	public int Del(int id) {
		int result = teachertypeMapper.Del(id);
		System.out.println("no:"+result);
		return result;
	}

	@Override
	public List<TeacherType> Get() {
		return teachertypeMapper.Get();
	}
	

}
