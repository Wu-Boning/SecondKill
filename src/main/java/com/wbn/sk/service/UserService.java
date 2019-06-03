package com.wbn.sk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbn.sk.dao.UserDao;
import com.wbn.sk.domain.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Transactional
	public boolean demoTransation() {
		User u1 = new User();
		u1.setId(2);
		u1.setName("Lily");
		userDao.insert(u1);
		
		User u2 = new User();
		u2.setId(1);
		u2.setName("Tom");
		userDao.insert(u2);
		
		return true;
		
	};
	
	public User getById(int id) {
		return userDao.getById(id);
	}
}
