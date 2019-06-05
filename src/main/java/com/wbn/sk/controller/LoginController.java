package com.wbn.sk.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wbn.sk.result.CodeMsg;
import com.wbn.sk.result.Result;
import com.wbn.sk.service.UserService;
import com.wbn.sk.vo.LoginVo;

@Controller
@RequestMapping("login")
public class LoginController {
	
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserService userService;	
	
	@RequestMapping("/to_login")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("/do_login")
	@ResponseBody
	public Result<Boolean> doLogin(@Valid LoginVo loginVo){
		log.info(loginVo.toString());
		//登录
		boolean res = userService.login(loginVo);
		return Result.success(true);		
	}
}
