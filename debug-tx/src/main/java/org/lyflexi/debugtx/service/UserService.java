package org.lyflexi.debugtx.service;

import org.lyflexi.debugtx.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void insertUser(){
		userDao.insert();
		//otherDao.other();xxx
		System.out.println("插入完成...");
		int i = 10/1;
	}
}
