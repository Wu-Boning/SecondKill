package com.wbn.sk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.wbn.sk.domain.OrderInfo;
import com.wbn.sk.domain.SecKillOrder;
import com.wbn.sk.domain.User;
import com.wbn.sk.result.CodeMsg;
import com.wbn.sk.service.GoodsService;
import com.wbn.sk.service.OrderService;
import com.wbn.sk.service.SeckillService;
import com.wbn.sk.vo.GoodsVo;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	SeckillService seckillService;
	
	@RequestMapping("/do_seckill")
	public String do_Seckill(Model model , User user,@RequestParam("goodsId")long goodsId) {
		System.out.println("test");
		if(user == null) {
			return "login";
		}
		//判断商品库存
		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
		Integer count = goods.getStockCount();
		if(count <= 0) {
			model.addAttribute("errmsg", CodeMsg.SECKILL_OVER.getMsg());
			return "seckill_fail";
		}
		//判断是否已经秒杀到了
		SecKillOrder order = orderService.getSeckillOrderByUserIdAndGoodsId(user.getId(), goodsId);
		if(order != null) {
			model.addAttribute("errmsg", CodeMsg.REPEATE_SECKILL.getMsg());
			return "seckill_fail";
		}
		//减库存，下订单，写入秒杀订单
		OrderInfo orderInfo = seckillService.seckill(user, goods);
		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("goods", goods);
		return "order_detail";
		
		
	}

}
