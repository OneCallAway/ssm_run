package com.java.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.ExamService;
@Service("ExamService")
public class ExamServiceiml implements ExamService {

	private ExamMapper examMapper;

	public ExamMapper getAddMapper() {
		return examMapper;
	}
	@Autowired
	public void setAddMapper(ExamMapper examMapper) {
		this.examMapper = examMapper;
	}
	
	@Override    
	public int Del(int id) {
		return examMapper.Del(id);
	}
	
	@Override    
	public Exam GetByID(int id) {
		return examMapper.GetByID(id);
	}

	
	@Override
	public int Add(Exam exam) {
		return examMapper.Add(exam);
	}
	
	@Override
	public int GetCount(Exam e) {
		return examMapper.GetCount(e);
	}

	@Override
	public List<Exam> Get(Exam e) {
		return examMapper.Get(e);
	}

}
