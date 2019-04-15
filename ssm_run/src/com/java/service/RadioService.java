package com.java.service;



import java.util.List;

import com.java.model.*;

public interface RadioService {

	public int Add(Radio j);
	public int Edit(Radio j);
	public Radio GetByID(int id);
	public int Del(int id);
	public List<Radio> Get(Radio n);
	public int GetCount(Radio j);
}
