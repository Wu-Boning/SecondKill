package com.wbn.sk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbn.sk.dao.UserDao;
import com.wbn.sk.domain.User;
import com.wbn.sk.exception.GlobalException;
import com.wbn.sk.result.CodeMsg;
import com.wbn.sk.util.MD5Util;
import com.wbn.sk.vo.LoginVo;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public User getByID(long id) {
		return userDao.getByID(id);
	}

	public boolean login(LoginVo loginVo) {
		if(loginVo == null) {
			throw new GlobalException(CodeMsg.SERVER_ERROR);
		}
		String moblie = loginVo.getMobile();
		String password = loginVo.getPassword();

		// 判断手机号是否存在
		User user = getByID(Long.valueOf(moblie));
		if (user == null) {
			throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
		}

		// 验证密码
		String dbSalt = user.getSalt();
		String dbPass = user.getPassword();
		String calcPss = MD5Util.formPassToDBPass(password, dbSalt);
		if (!calcPss.equals(dbPass)) {
			throw new GlobalException(CodeMsg.PASSWORD_ERROR);
		}
		return true;

	}

}
