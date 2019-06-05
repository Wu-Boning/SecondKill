package com.wbn.sk.redis;

/**
 * 	前缀接口
 * @author WuBN
 *
 */
public interface KeyPrefix {
	
	public int getExpireSeconds();
	
	public String getPerfix();

}
