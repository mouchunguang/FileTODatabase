package com.pbccrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbccrc.mapper.HouseMapper;
import com.pbccrc.po.HousePO;

@Service("HouseBusiService")
public class HouseBusiService {
	@Autowired
	private HouseMapper houseMapper;
	
	public HousePO selectHouseByID(Long houseID) {
		return  houseMapper.selectHouseByID(houseID);
	}
	    
	public void deleteHouseByID(Long houseID) {
		houseMapper.deleteHouseByID(houseID);
	}
}
