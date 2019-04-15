package com.java.service;



import java.util.List;

import com.java.model.*;

public interface ExamPanDuanService {

	public int Add(ExamPanDuan j);


	public int Del(int id);
	public List<ExamPanDuan> Get(ExamPanDuan n);
	public int GetCount(ExamPanDuan j);
}
