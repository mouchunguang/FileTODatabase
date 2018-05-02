package com.pbccrc.mapper;

import org.apache.ibatis.annotations.Param;

import com.pbccrc.po.HousePO;

public interface HouseMapper {
   public HousePO selectHouseByID(@Param("houseID") Long houseID);
    
   public void deleteHouseByID(@Param("houseID") Long houseID); 
}