package com.java.service;



import java.util.List;

import com.java.model.*;

public interface PanDuanService {

	public int Add(PanDuan j);
	public int Edit(PanDuan j);
	public PanDuan GetByID(int id);
	public int Del(int id);
	public List<PanDuan> Get(PanDuan n);
	public int GetCount(PanDuan j);
}
