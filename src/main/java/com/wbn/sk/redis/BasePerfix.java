package com.wbn.sk.redis;

/**
 *	 前缀基类
 * @author WuBN
 *
 */
public abstract class BasePerfix implements KeyPrefix{
	
	private int expireSeconds;
	
	private String perfix;
	
	public BasePerfix(String perfix) {
		this(0, perfix);
	}
	
	public BasePerfix(int expireSeconds, String perfix) {
		this.expireSeconds = expireSeconds;
		this.perfix  = perfix;
	}
	
	@Override
	public String getPerfix() {
		String className = getClass().getSimpleName();
		return className + ":" + perfix + "_";
	}
	
	/**
	 * 默认0代表永不过期
	 */
	@Override
	public int getExpireSeconds() {
		return expireSeconds;
	}

}
