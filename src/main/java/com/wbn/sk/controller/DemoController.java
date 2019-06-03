package com.wbn.sk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.wbn.sk.domain.User;
import com.wbn.sk.redis.UserKey;
import com.wbn.sk.result.CodeMsg;
import com.wbn.sk.result.Result;
import com.wbn.sk.service.RedisService;
import com.wbn.sk.service.UserService;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	RedisService redisService;
	
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello World";
	}
	
	@RequestMapping("/hello")
	@ResponseBody
	public Result<String> hello(){
		return Result.success("Success!");
	}
	
	@RequestMapping("/helloError")
	@ResponseBody
	public Result<String> helloError(){
		return Result.error(CodeMsg.SERVER_ERROR);
	}
	
	@RequestMapping("/thymeleaf")
	public String thymeleaf(Model model){
		model.addAttribute("name", "wbn");
		return "hello";
	}
	
	@RequestMapping("/db/get")
	@ResponseBody
	public Result<User> dbGet() {
		User user = userservice.getById(1);
		return Result.success(user);
	}
	
	@RequestMapping("/db/tx")
	public Result<Boolean> dbTransation(){		
		userservice.demoTransation();
		return Result.success(true);
	}
	
	@RequestMapping("/redis/get")
	@ResponseBody
	public Result<User> redisGet(){
		User user = redisService.get(UserKey.getById, "1", User.class);
		return Result.success(user);
	}
	
	@RequestMapping("/redis/set")
	@ResponseBody
	public Result<Boolean> redisSet(){
		User user = new User(1, "Bob");
		boolean result = redisService.set(UserKey.getById, "1", user);

		return Result.success(result);
	}
	
	

}
