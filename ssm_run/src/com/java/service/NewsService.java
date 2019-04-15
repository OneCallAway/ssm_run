package com.java.service;



import java.util.List;

import com.java.model.*;

public interface NewsService {

	public int Add(News j);
	public int Edit(News j);
	public News GetByID(int id);
	public int Del(int id);
	public List<News> Get(News n);
	public int EditState(News n);
	public int GetCount(News j);
}
