package com.wbn.sk.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.wbn.sk.domain.User;
import com.wbn.sk.result.CodeMsg;
import com.wbn.sk.result.Result;
import com.wbn.sk.service.GoodsService;
import com.wbn.sk.service.UserService;
import com.wbn.sk.vo.GoodsVo;
import com.wbn.sk.vo.LoginVo;

@Controller
@RequestMapping("/goods")
public class GoodController {

	private static Logger log = LoggerFactory.getLogger(GoodController.class);
	
	@Autowired
	GoodsService goodsService;

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
		List<GoodsVo> goodsList = goodsService.listGoodsVo();
		model.addAttribute("goodsList", goodsList);
		return "goods_list";
	}
	
	@RequestMapping("/to_detail/{goodsId}")
	public String detail(Model model, User user, @PathVariable("goodsId")long goodsId) {
		model.addAttribute("user", user);
		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
		System.out.println(goods);
		model.addAttribute("goods", goods);
		long satrtAt = goods.getStartDate().getTime();
		long endAt = goods.getEndDate().getTime();
		long now = System.currentTimeMillis();
		
		//秒杀状态
		int secKillStatus = 0;
		//倒计时开始时间
		int remainSec = 0;
		
		if(now<satrtAt) {
			//秒杀还没开始，倒计时
			secKillStatus = 0;
			remainSec = (int) ((satrtAt - now)/1000);
		}else if(now>endAt) {
			//秒杀结束
			secKillStatus = 2;
			remainSec = -1;
		}else {
			//秒杀进行中
			secKillStatus = 1;
			remainSec = 0;
		}
		model.addAttribute("secKillStatus", secKillStatus);
		model.addAttribute("remainSeconds", remainSec);
		return "goods_detail";
		
	}
	
	

}
