package com.wbn.sk.result;

/**
 * 	服务器返回结果对象
 * @author WuBN
 *
 * @param <T> 返回到客户端的数据
 */
public class Result<T> {
	
	private int code;
	
	private String msg;
	
	private T data;
	
	public static <T> Result<T> success(T data){
		return new Result<T>(data);
	}
	
	public static <T> Result<T> error(CodeMsg codeMsg){
		return new Result<T>(codeMsg);
	}
	
	private Result(T data){
		this.code = 0;
		this.msg = "success";
		this.data = data;
	}

	private Result(CodeMsg codeMsg){
		if(codeMsg == null) {
			return;
		}
		this.code = codeMsg.getCode();
		this.msg = codeMsg.getMsg();
	}
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}
	
	

}
