package com.mall.jiuzhenbao.validation;

import com.mall.jiuzhenbao.security.exception.BeanValidationErrorCode;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Documented
@Constraint(validatedBy = UUIDValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, CONSTRUCTOR,TYPE_USE })
@Retention(RUNTIME)
public @interface UUIDFormat {

	String message() default BeanValidationErrorCode.UUID_FORMAT;
	
	boolean ignoreBlank() default true;
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
