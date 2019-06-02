package com.wbn.sk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.wbn.sk.result.CodeMsg;
import com.wbn.sk.result.Result;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
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

}
