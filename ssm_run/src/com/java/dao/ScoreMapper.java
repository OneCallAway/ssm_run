package com.java.dao;

import java.util.List;

import com.java.model.Score;

public interface ScoreMapper {
    int Del(Integer id);

    int Add(Score record);
    int EditScore(Score record);
    int GetCount(Score record);

    List<Score> Get(Score record);

}