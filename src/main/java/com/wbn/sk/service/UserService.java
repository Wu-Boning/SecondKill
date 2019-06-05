package com.wbn.sk.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.wbn.sk.dao.UserDao;
import com.wbn.sk.domain.User;
import com.wbn.sk.exception.GlobalException;
import com.wbn.sk.redis.UserKey;
import com.wbn.sk.result.CodeMsg;
import com.wbn.sk.util.MD5Util;
import com.wbn.sk.util.UUIDUtil;
import com.wbn.sk.vo.LoginVo;

@Service
public class UserService {
	
	public static final String COOKI_NAME_TOKEN = "token";

	@Autowired
	UserDao userDao;
	
	@Autowired
	RedisService redisService;

	public User getByID(long id) {
		return userDao.getByID(id);
	}

	/**
	 * 登录服务
	 * @param response
	 * @param loginVo
	 * @return
	 */
	public boolean login(HttpServletResponse response, LoginVo loginVo) {
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
		
		//生成cookie
		String token = UUIDUtil.uuid();
		addCookie(response, token, user);		
		return true;

	}

	/**
	 * 生成cookie
	 * @param response
	 * @param token 
	 * @param user
	 */
	public void addCookie(HttpServletResponse response, String token, User user) {
		//将cookie存到redis中，实现分布式
		redisService.set(UserKey.token, token, user);
		Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
		cookie.setMaxAge(UserKey.token.getExpireSeconds());
		cookie.setPath("/");
		//将cookie存到response对象中
		response.addCookie(cookie);
	}

	/**
	 * 获取Redis中的user对象
	 * @param token
	 * @return
	 */
	public User getByToken(HttpServletResponse response, String token) {
		if(StringUtils .isEmpty(token)) {
			return null;
		}
		//去redis中根据cookie查找user
		User user = redisService.get(UserKey.token, token, User.class);
		//延长有效期
		if(user != null) {
			addCookie(response, token, user);
		}
		
		return user;
	}

}
