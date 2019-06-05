package com.wbn.sk.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密工具
 * @author WuBN
 *
 */
public class MD5Util {
	
	private static final String SALT = "1a2b3c4d";

	public static String md5(String src) {
		return DigestUtils.md5Hex(src); 
	}
	
	public static String inputPassToFormPass(String inputPass) {
		String password = SALT.substring(0, SALT.length() / 2)
                + inputPass + SALT.substring(SALT.length() / 2);
		return md5(password);
	}	
	
	public static String formPassToDBPass(String formPass, String salt) {
		String password =SALT.substring(0, SALT.length() / 2)
                + formPass + SALT.substring(SALT.length() / 2);
		return md5(password);
	}
	
	public static String inputPassToDBPass(String input, String saltDB) {
		String formPass = inputPassToFormPass(input);
		return formPassToDBPass(formPass, saltDB);
	}
	
//	public static void main(String[] args) {
//		//138e0ba63fe4248bb6dbcf5f9b3b4b57
//		System.out.println(inputPassToDBPass("123456", "1a2b3c4d"));
//	}
}
