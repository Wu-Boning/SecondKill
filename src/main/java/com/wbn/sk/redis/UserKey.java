package com.wbn.sk.redis;

public class UserKey extends BasePerfix{

	private UserKey(String perfix) {
		super(perfix);
	}
	
	public static UserKey getById = new UserKey("id");
	public static UserKey getByName = new UserKey("name");

	
}
