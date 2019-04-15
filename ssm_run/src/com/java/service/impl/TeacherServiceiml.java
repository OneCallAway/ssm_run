package com.java.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.TeacherService;
@Service("TeacherService")
public class TeacherServiceiml implements TeacherService {

	private TeacherMapper teacherMapper;

	public TeacherMapper getAddMapper() {
		return teacherMapper;
	}
	@Autowired
	public void setAddMapper(TeacherMapper teacherMapper) {
		this.teacherMapper = teacherMapper;
	}
	
	@Override    
	public int Edit(Teacher teacher) {
		return teacherMapper.Edit(teacher);
	}
	
	@Override    
	public int EditPass(Teacher teacher) {
		return teacherMapper.EditPass(teacher);
	}
	
	@Override
	public Teacher GetByID(int id) {
		return teacherMapper.GetByID(id);
	}
	
	@Override
	public Teacher Login(Teacher teacher) {
		return teacherMapper.Login(teacher);
	}
	
	@Override
	public int Add(Teacher teacher) {
		return teacherMapper.Add(teacher);
	}
	
	@Override
	public int Del(int id) {
		int result = teacherMapper.Del(id);
		return result;
	}

	@Override
	public List<Teacher> Get(Teacher teacher) {
		return teacherMapper.Get(teacher);
	}
	
	@Override
	public int GetCount(Teacher teacher) {
		return teacherMapper.GetCount(teacher);
	}

}
