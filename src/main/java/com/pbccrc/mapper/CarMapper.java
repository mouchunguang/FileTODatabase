package com.pbccrc.mapper;

import org.apache.ibatis.annotations.Param;

import com.pbccrc.po.CarPO;

public interface CarMapper {
    public CarPO selectCarByID(@Param("carID")Long carID);
    public void deleteCarByID(@Param("carID")Long carID);
}