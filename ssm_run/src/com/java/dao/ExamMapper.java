package com.java.dao;

import java.util.List;

import com.java.model.Exam;

public interface ExamMapper {
    int Del(Integer id);

    int Add(Exam record);
    Exam GetByID(int id);
    int GetCount(Exam record);

    List<Exam> Get(Exam record);
}