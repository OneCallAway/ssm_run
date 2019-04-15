package com.java.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.ExamPanDuanService;
@Service("ExamPanDuanService")
public class ExamPanDuanServiceiml implements ExamPanDuanService {

	private ExamPanDuanMapper examMapper;

	public ExamPanDuanMapper getAddMapper() {
		return examMapper;
	}
	@Autowired
	public void setAddMapper(ExamPanDuanMapper examMapper) {
		this.examMapper = examMapper;
	}
	
	@Override    
	public int Del(int id) {
		return examMapper.Del(id);
	}

	
	@Override
	public int Add(ExamPanDuan exam) {
		return examMapper.Add(exam);
	}
	
	@Override
	public int GetCount(ExamPanDuan e) {
		return examMapper.GetCount(e);
	}

	@Override
	public List<ExamPanDuan> Get(ExamPanDuan e) {
		return examMapper.Get(e);
	}

}
