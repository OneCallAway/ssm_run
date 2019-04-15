package com.java.dao;

import java.util.List;

import com.java.model.Radio;

public interface RadioMapper {
    int Del(Integer id);

    int Add(Radio record);

    int Edit(Radio record);

    Radio GetByID(Integer id);

    int GetCount(Radio record);

    List<Radio> Get(Radio record);
}