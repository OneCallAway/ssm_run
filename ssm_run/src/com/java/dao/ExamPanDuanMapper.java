package com.java.dao;

import java.util.List;

import com.java.model.ExamPanDuan;

public interface ExamPanDuanMapper {
    int Del(Integer id);

    int Add(ExamPanDuan record);

    int GetCount(ExamPanDuan record);

    List<ExamPanDuan> Get(ExamPanDuan record);

}