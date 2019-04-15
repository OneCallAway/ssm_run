package com.java.service;



import java.util.List;

import com.java.model.*;

public interface ExamRadioService {

	public int Add(ExamRadio j);


	public int Del(int id);
	public List<ExamRadio> Get(ExamRadio n);
	public int GetCount(ExamRadio j);
}
