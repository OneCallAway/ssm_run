package com.java.service;



import java.util.List;

import com.java.model.*;

public interface TeacherService {

	public int Add(Teacher r);
	public int Edit(Teacher r);
	public Teacher GetByID(int id);
	public int Del(int id);
	public List<Teacher> Get(Teacher s);
	public int GetCount(Teacher s);
	public Teacher Login(Teacher s);
	public int EditPass(Teacher s);
}
