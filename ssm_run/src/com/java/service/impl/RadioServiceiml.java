package com.java.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.*;
import com.java.model.*;
import com.java.service.RadioService;
@Service("RadioService")
public class RadioServiceiml implements RadioService {

	private RadioMapper radioMapper;

	public RadioMapper getAddMapper() {
		return radioMapper;
	}
	@Autowired
	public void setAddMapper(RadioMapper radioMapper) {
		this.radioMapper = radioMapper;
	}
	
	@Override    
	public int Edit(Radio radio) {
		return radioMapper.Edit(radio);
	}
	

	
	@Override
	public Radio GetByID(int id) {
		return radioMapper.GetByID(id);
	}
	
	@Override
	public int Add(Radio radio) {
		return radioMapper.Add(radio);
	}
	
	@Override
	public int Del(int id) {
		int result = radioMapper.Del(id);
		System.out.println("no:"+result);
		return result;
	}

	@Override
	public List<Radio> Get(Radio n) {
		return radioMapper.Get(n);
	}

	@Override
	public int GetCount(Radio n) {
		return radioMapper.GetCount(n);
	}
}
