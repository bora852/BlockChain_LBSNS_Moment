package com.travelmaker.controller;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.travelmaker.dto.User;
public interface IUserService {
	
	List<User> selectAllUser();
	User selectByUid(int uid);
	User selectByEmail(String email);
	
	@Transactional
	User insertUser(User user);
	@Transactional
	User deleteUser(int uid);
	@Transactional
	User modifyUser(User user);
}