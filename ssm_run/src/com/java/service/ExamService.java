package com.java.service;



import java.util.List;

import com.java.model.*;

public interface ExamService {

	public int Add(Exam j);
	public Exam GetByID(int id);

	public int Del(int id);
	public List<Exam> Get(Exam n);
	public int GetCount(Exam j);
}
