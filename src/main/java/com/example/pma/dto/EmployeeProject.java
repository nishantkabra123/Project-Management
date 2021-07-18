package com.example.pma.dto;

public interface EmployeeProject {

	//Due to spring data we dont need to create variable getter,setter,constructor,etc need to have the property names begin with get
	public String getFirstName();
	public String getLastName();
	public int getProjectCount();
	
}
