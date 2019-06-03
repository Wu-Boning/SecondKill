package com.wbn.sk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wbn.sk.redis.KeyPrefix;

import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
	
	@Autowired
	JedisPool jedisPool;	
	
	/**
	 * 获取一个对象
	 * @param <T>	
	 * @param perfix	key前缀
	 * @param key
	 * @param clazz	对象类型
	 * @return
	 */
	public <T> T get(KeyPrefix perfix, String key, Class<T> clazz) {
		redis.clients.jedis.Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//生成真正的key
			String realKey = perfix.getPerfix() + key;
			String str = jedis.get(realKey);
			T t = stringToBean(str, clazz);
			return t;
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 * 设置对象
	 * @param <T>
	 * @param perfix
	 * @param key
	 * @param value	值
	 * @return
	 */
	public <T> boolean set(KeyPrefix perfix, String key, T value) {
		redis.clients.jedis.Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String str = beanToString(value);
			if(str == null || str.length() == 0) {
				return false;
			}
			//生成真正的key
			String realKey = perfix.getPerfix() + key;
			int expireSeconds = perfix.getExpireSeconds();
			if(expireSeconds <= 0) {
				jedis.set(realKey, str);				
			}else {
				jedis.setex(realKey, expireSeconds, str);
			}
			return true;
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 * 判断key是否存在
	 * @param perfix
	 * @param key
	 * @return
	 */
	public boolean exists(KeyPrefix perfix, String key) {
		redis.clients.jedis.Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//生成真正的key
			String realKey = perfix.getPerfix() + key;
			return jedis.exists(realKey);			
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 * 自增
	 * @param perfix
	 * @param key
	 * @return
	 */
	public Long incr(KeyPrefix perfix, String key) {
		redis.clients.jedis.Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//生成真正的key
			String realKey = perfix.getPerfix() + key;
			return jedis.incr(realKey);			
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 * 自减
	 * @param perfix
	 * @param key
	 * @return
	 */
	public Long decr(KeyPrefix perfix, String key) {
		redis.clients.jedis.Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//生成真正的key
			String realKey = perfix.getPerfix() + key;
			return jedis.decr(realKey);			
		} finally {
			returnToPool(jedis);
		}
	}
	
	
	private <T> String beanToString(T value) {
		if(value == null)
			return null;
		Class<?> clazz = value.getClass();
		if(clazz == int.class || clazz == Integer.class) {
			return String.valueOf(value);
		}else if (clazz == String.class) {
			return String.valueOf(value);
		}else if(clazz == long.class || clazz == Long.class) {
			return String.valueOf(value);
		}else {
			return JSON.toJSONString(value);
		}
	}

	private <T> T stringToBean(String str, Class<T> clazz) {
		if(str == null || str.length() == 0 || clazz == null) {
			return null;
		}
		if(clazz == int.class || clazz == Integer.class) {
			return (T) Integer.valueOf(str);
		}else if (clazz == String.class) {
			return (T) str;
		}else if(clazz == long.class || clazz == Long.class) {
			return (T) Long.valueOf(str);
		}else {
			return JSON.toJavaObject(JSON.parseObject(str), clazz);
		}
	}


	private void returnToPool(redis.clients.jedis.Jedis jedis) {
		if(jedis != null) {
			jedis.close();
		}
	}


}
