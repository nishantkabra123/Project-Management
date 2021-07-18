package com.example.pma.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//only applicable on fields of a class
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)//to make retention available in byte code for reflection
@Constraint(validatedBy=UniqueValidator.class)
public @interface UniqueValue {

	String message() default "Unique Constraint violated";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
	
}
 