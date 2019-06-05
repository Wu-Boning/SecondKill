package com.wbn.sk.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.wbn.sk.domain.User;
import com.wbn.sk.result.CodeMsg;
import com.wbn.sk.result.Result;
import com.wbn.sk.service.UserService;
import com.wbn.sk.vo.LoginVo;

@Controller
@RequestMapping("/goods")
public class GoodController {

	private static Logger log = LoggerFactory.getLogger(GoodController.class);

	@Autowired
	UserService userService;

	/**
	 * 商品页面
	 * 使用WebConfig配置UserArgumentResolver,自动将
	 * @param model
	 * @param cookieToken
	 * @param paramToken 移动端cookie
	 * @return
	 */
	@RequestMapping("/list")
	public String tolist(Model model, //HttpServletResponse response,
//			@CookieValue(value = UserService.COOKI_NAME_TOKEN, required = false) String cookieToken,
//			@RequestParam(value = UserService.COOKI_NAME_TOKEN, required = false) String paramToken,
			User user) {
//		if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
//			return "login";
//		}
//		String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
//		User user = userService.getByToken(response, token);
		model.addAttribute("user", user);
		return "goods_list";
	}

}
