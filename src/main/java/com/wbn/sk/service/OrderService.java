package com.wbn.sk.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbn.sk.dao.OrderDao;
import com.wbn.sk.domain.OrderInfo;
import com.wbn.sk.domain.SecKillOrder;
import com.wbn.sk.domain.User;
import com.wbn.sk.vo.GoodsVo;

@Service
public class OrderService {
	
	@Autowired
	OrderDao orderDao;

	public SecKillOrder getSeckillOrderByUserIdAndGoodsId(Long userId, long goodsId) {
		return orderDao.getSeckillOrderByUserIdAndGoodsId(userId, goodsId);
	}

	@Transactional
	public OrderInfo createOrder(User user, GoodsVo goods) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getSeckillPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		orderInfo.setUserId(user.getId());		
		long orderId = orderDao.insert(orderInfo);
		
		SecKillOrder secKillOrder = new SecKillOrder();
		secKillOrder.setGoodsId(goods.getId());
		secKillOrder.setOrderId(orderId);
		secKillOrder.setUserId(user.getId());
		orderDao.inserSeckillOrder(secKillOrder);
		return orderInfo;
	}

	
}
