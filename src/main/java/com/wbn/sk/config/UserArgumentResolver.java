package com.wbn.sk.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.druid.util.StringUtils;
import com.wbn.sk.domain.User;
import com.wbn.sk.service.UserService;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver{
	
	@Autowired
	UserService userService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> clazz = parameter.getParameterType();
		return clazz == User.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		//获取request对象
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		//获取response对象
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		//获取参数
		String paramToken = request.getParameter(UserService.COOKI_NAME_TOKEN);
		//获取request中的cookie
		String cookieToken = getCookieValue(request, UserService.COOKI_NAME_TOKEN);
		//如果没有则返回登录页面
		if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return "login";
		}
		String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
		return userService.getByToken(response, token);
	}

	/**
	 * 根据名字找到cookie
	 * @param request
	 * @param cookiNameToken
	 * @return
	 */
	private String getCookieValue(HttpServletRequest request, String cookiNameToken) {
		Cookie[] cookies = request.getCookies();
		if(cookies == null || cookies.length<=0) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals(cookiNameToken)) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
