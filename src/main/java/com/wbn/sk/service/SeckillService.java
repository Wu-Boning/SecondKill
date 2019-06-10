package com.wbn.sk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbn.sk.dao.GoodsDao;
import com.wbn.sk.domain.Goods;
import com.wbn.sk.domain.OrderInfo;
import com.wbn.sk.domain.User;
import com.wbn.sk.vo.GoodsVo;

@Service
public class SeckillService {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;

	@Transactional
	public OrderInfo seckill(User user, GoodsVo goods) {
		//减少库存
		goodsService.reduceStock(goods);
		
		return orderService.createOrder(user,goods);
		
	}

}
