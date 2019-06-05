package com.wbn.sk.redis;

/**
 * user的key 前缀实现类
 * @author WuBN
 *
 */
public class UserKey extends BasePerfix{

	private UserKey(String perfix) {
		super(perfix);
	}
	
	public static UserKey getById = new UserKey("id");
	public static UserKey getByName = new UserKey("name");

	
}
