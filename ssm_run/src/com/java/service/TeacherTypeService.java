package com.java.service;



import java.util.List;

import com.java.model.*;

public interface TeacherTypeService {

	public int Add(TeacherType j);
	public int Edit(TeacherType j);
	public TeacherType GetByID(int id);
	public int Del(int id);
	public List<TeacherType> Get();

}
