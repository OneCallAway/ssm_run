package com.java.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.PanDuanService;
@Service("PanDuanService")
public class PanDuanServiceiml implements PanDuanService {

	private PanDuanMapper panduanMapper;

	public PanDuanMapper getAddMapper() {
		return panduanMapper;
	}
	@Autowired
	public void setAddMapper(PanDuanMapper panduanMapper) {
		this.panduanMapper = panduanMapper;
	}
	
	@Override    
	public int Edit(PanDuan panduan) {
		return panduanMapper.Edit(panduan);
	}
	

	
	@Override
	public PanDuan GetByID(int id) {
		return panduanMapper.GetByID(id);
	}
	
	@Override
	public int Add(PanDuan panduan) {
		return panduanMapper.Add(panduan);
	}
	
	@Override
	public int Del(int id) {
		int result = panduanMapper.Del(id);
		System.out.println("no:"+result);
		return result;
	}

	@Override
	public List<PanDuan> Get(PanDuan n) {
		return panduanMapper.Get(n);
	}

	@Override
	public int GetCount(PanDuan n) {
		return panduanMapper.GetCount(n);
	}
}
