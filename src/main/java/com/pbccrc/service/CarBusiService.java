package com.pbccrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pbccrc.mapper.CarMapper;
import com.pbccrc.po.CarPO;

@Service("CarBusiService")
public class CarBusiService {
	@Autowired
	private CarMapper carMapper;
	public CarPO selectCarByID(Long carID) {
		return carMapper.selectCarByID(carID);
	}
    public void deleteCarByID(Long carID) {
    	carMapper.deleteCarByID(carID);;
    }
}
