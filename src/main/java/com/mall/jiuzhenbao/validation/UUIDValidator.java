package com.mall.jiuzhenbao.validation;

import com.mall.jiuzhenbao.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UUIDValidator implements ConstraintValidator<UUIDFormat, String> {

	boolean ingoreBlank=true;
	
	  @Override
	  public boolean isValid(String value, ConstraintValidatorContext context) {
		  if(value==null||value.isEmpty()) {
			 return ingoreBlank;
		  }else{
			 return  StringUtils.isUUID(value);
		  } 
	  }

	@Override
	public void initialize(UUIDFormat arg0) {
		ingoreBlank=arg0.ignoreBlank();
	}

}