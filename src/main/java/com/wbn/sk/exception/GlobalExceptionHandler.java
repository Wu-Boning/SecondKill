package com.wbn.sk.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wbn.sk.result.CodeMsg;
import com.wbn.sk.result.Result;

/**
 * 	全局异常处理器
 * @author WuBN
 *
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
		if(e instanceof BindException) {
			BindException ex = (BindException) e;
			List<ObjectError> erroes = ex.getAllErrors();
			ObjectError eroor = erroes.get(0);
			String msg = eroor.getDefaultMessage();
			return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
		}else if(e instanceof GlobalException) {
			GlobalException ex = (GlobalException) e;
			return Result.error(ex.getCodeMsg());
		}
		else {
			e.printStackTrace();
			return Result.error(CodeMsg.SERVER_ERROR);
		}
	}

}
