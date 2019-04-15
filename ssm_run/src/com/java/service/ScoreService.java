package com.java.service;



import java.util.List;

import com.java.model.*;

public interface ScoreService {

	public int Add(Score j);

	public int EditScore(Score record);
	public int Del(int id);
	public List<Score> Get(Score n);
	public int GetCount(Score j);
}
