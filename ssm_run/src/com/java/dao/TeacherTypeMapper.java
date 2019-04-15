package com.java.dao;

import java.util.List;

import com.java.model.TeacherType;

public interface TeacherTypeMapper {
	int Del(Integer id);

    int Add(TeacherType record);

    TeacherType GetByID(Integer id);

    int Edit(TeacherType record);

    List<TeacherType> Get();
}