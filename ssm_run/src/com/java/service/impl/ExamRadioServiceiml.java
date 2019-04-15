package com.java.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.ExamRadioService;
@Service("ExamRadioService")
public class ExamRadioServiceiml implements ExamRadioService {

	private ExamRadioMapper examMapper;

	public ExamRadioMapper getAddMapper() {
		return examMapper;
	}
	@Autowired
	public void setAddMapper(ExamRadioMapper examMapper) {
		this.examMapper = examMapper;
	}
	
	@Override    
	public int Del(int id) {
		return examMapper.Del(id);
	}

	
	@Override
	public int Add(ExamRadio exam) {
		return examMapper.Add(exam);
	}
	
	@Override
	public int GetCount(ExamRadio e) {
		return examMapper.GetCount(e);
	}

	@Override
	public List<ExamRadio> Get(ExamRadio e) {
		return examMapper.Get(e);
	}

}
