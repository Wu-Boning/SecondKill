package com.wbn.sk.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;import com.alibaba.druid.util.StringUtils;

/**
 * 电话号码效验器
 * @author WuBN
 *
 */
public class IsMoblieValidator implements ConstraintValidator<IsMoblie, String>{
	
	private boolean required = false;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(required) {
			return ValidatorUtil.isMobile(value);
		}else {
			if(StringUtils.isEmpty(value)) {
				return true;
			}else {
				return ValidatorUtil.isMobile(value);
			}
		}
	}
	@Override
	public void initialize(IsMoblie constraintAnnotation) {
		required = constraintAnnotation.required();
	}
	

}
