package com.java.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.ScoreService;
@Service("ScoreService")
public class ScoreServiceiml implements ScoreService {

	private ScoreMapper scoreMapper;

	public ScoreMapper getAddMapper() {
		return scoreMapper;
	}
	@Autowired
	public void setAddMapper(ScoreMapper scoreMapper) {
		this.scoreMapper = scoreMapper;
	}
	
	@Override    
	public int Del(int id) {
		return scoreMapper.Del(id);
	}

	
	@Override
	public int Add(Score score) {
		return scoreMapper.Add(score);
	}
	
	@Override
	public int EditScore(Score score) {
		return scoreMapper.EditScore(score);
	}
	
	@Override
	public int GetCount(Score e) {
		return scoreMapper.GetCount(e);
	}

	@Override
	public List<Score> Get(Score e) {
		return scoreMapper.Get(e);
	}

}
