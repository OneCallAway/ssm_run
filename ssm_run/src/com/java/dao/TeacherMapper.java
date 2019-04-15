package com.java.dao;

import java.util.List;

import com.java.model.Teacher;

public interface TeacherMapper {
    int Del(Integer id);

    int Add(Teacher record);


    Teacher GetByID(Integer id);

    List<Teacher> Get(Teacher record);

    int GetCount(Teacher record);
    Teacher Login(Teacher record);
    int Edit(Teacher record);
    int EditPass(Teacher record);
}