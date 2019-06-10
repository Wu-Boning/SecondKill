package com.wbn.sk.redis;

/**
 * user的key 前缀实现类
 * @author WuBN
 *
 */
public class UserKey extends BasePerfix{
	
	public static final int TOKEN_EXPIRE = 600; 
	

	private UserKey(int expireSeconds, String perfix) {
		super(expireSeconds, perfix);
	}
	
	public static UserKey token = new UserKey(TOKEN_EXPIRE, "tk");

	
}
