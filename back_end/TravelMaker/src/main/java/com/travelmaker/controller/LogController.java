package com.travelmaker.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.ibatis.javassist.bytecode.ByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.User;
import com.travelmaker.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class LogController {
	
	@Autowired
	private UserService userService;

	//회원 전체 조회
	@RequestMapping(value = "/log", method = RequestMethod.GET)
	@ApiOperation(value = "로그 조회")
	public byte[] selectAllUser() throws Exception {
		File f = new File("logs/travelMaker.log");
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			return null;
		}
		byte byteArray[] = new byte[(int) f.length()];
		fis.read(byteArray);
		fis.close();
		return byteArray;
	}
}
