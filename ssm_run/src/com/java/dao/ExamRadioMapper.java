package com.java.dao;

import java.util.List;

import com.java.model.ExamRadio;

public interface ExamRadioMapper {
    int Del(Integer id);

    int Add(ExamRadio record);

    int GetCount(ExamRadio record);

    List<ExamRadio> Get(ExamRadio record);

}