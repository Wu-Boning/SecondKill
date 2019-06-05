package com.wbn.sk.result;

import org.apache.ibatis.javassist.compiler.ast.CondExpr;

/**
 * 	返回信息的代码及信息内容
 * @author WuBN
 *
 */
public class CodeMsg {
	
	private int code;
	private String msg;
	
	//通用异常
	public static final CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static final CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
	public static final CodeMsg BIND_ERROR = new CodeMsg(500101, "参数效验异常：%s");
	//登录模块5002XX
	public static final CodeMsg LOGINBO_NULL = new CodeMsg(500210, "用户为空");
	public static final CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "用户密码为空");
	public static final CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "用户手机号为空");
	public static final CodeMsg MOBILE_ERROR = new CodeMsg(500213, "用户手机号格式错误");
	public static final CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "用户手机号不存在");
	public static final CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "用户密码错误");
	
	
	//商品模块5003XX
	
	//订单模块5004XX
	
	//秒杀模块5005XX
	
	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
	public CodeMsg fillArgs(Object...args) {
		int code = this.code;
		String msg = String.format(this.msg, args);
		return new CodeMsg(code, msg);
	}
	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}
	
	
	

}
