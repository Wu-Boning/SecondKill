package com.wbn.sk.exception;

import com.wbn.sk.result.CodeMsg;

public class GlobalException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private CodeMsg codeMsg;
	
	public GlobalException(CodeMsg cm) {
		super(cm.toString());
		this.codeMsg = cm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CodeMsg getCodeMsg() {
		return codeMsg;
	}
	
	

}
