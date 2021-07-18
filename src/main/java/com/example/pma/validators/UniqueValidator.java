package com.example.pma.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.entities.Employee;

public class UniqueValidator implements ConstraintValidator<UniqueValue,String> {
//String is datatype of email which we want to validate
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println("Enter validation method..");
		Employee emp=empRepo.findByEmail(value);
		if(emp!=null) {
			return false;
		}else {
			return true;
		}
	}
	
}
