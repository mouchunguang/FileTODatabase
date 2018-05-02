package com.pbccrc.mapper;

import org.apache.ibatis.annotations.Param;

import com.pbccrc.po.UserPO;

public interface UserMapper {
    public void deleteByUserID(@Param("userID") Long userID);
    
    public UserPO selectUserByID(@Param("userID") Long userID);

}