package com.wbn.sk.redis;

public interface KeyPrefix {
	
	public int getExpireSeconds();
	
	public String getPerfix();

}
