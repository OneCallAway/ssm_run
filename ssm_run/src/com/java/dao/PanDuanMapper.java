package com.java.dao;

import java.util.List;

import com.java.model.PanDuan;

public interface PanDuanMapper {
    int Del(Integer id);

    int Add(PanDuan record);

    int Edit(PanDuan record);

    PanDuan GetByID(Integer id);

    int GetCount(PanDuan record);

    List<PanDuan> Get(PanDuan record);
}