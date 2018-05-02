package com.pbccrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbccrc.mapper.UserMapper;
import com.pbccrc.po.UserPO;

@Service("UserBusiService")
public class UserBusiService {
	 @Autowired
	 private UserMapper userMapper;
	 public void deleteByUserID(Long userID) {
		 userMapper.deleteByUserID(userID);
	 }
	    
	 public UserPO selectUserByID(Long userID) {
		 return  userMapper.selectUserByID(userID);
	 }
}
