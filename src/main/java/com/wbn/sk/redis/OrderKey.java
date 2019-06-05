package com.wbn.sk.redis;

/**
 * 	订单的key,前缀实现类
 * @author WuBN
 *
 */
public class OrderKey extends BasePerfix{

	public OrderKey(int expireSeconds, String perfix) {
		super(expireSeconds, perfix);
		// TODO Auto-generated constructor stub
	}

}
