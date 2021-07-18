package com.example.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Employee;

@Service
public class EmployeeService {

	//Field Injection
//	@Qualifier("staffRepositoryImpl1")
	@Autowired
	EmployeeRepository empRepo;
	
	public Employee save(Employee emp) {
		return empRepo.save(emp);
	}
	
	public List<Employee> getAll(){
		return (List<Employee>)empRepo.findAll();
	}
	
	public List<EmployeeProject> employeeProjects() {
		return empRepo.employeeProjects();
	}
	
//	IStaffRepo staffRepo;	
	
//	constructor injection.We can use @Primary above @Repository to mark as primary bean
//	staff bean should be lowercase as spring stores it that way.
//	public EmployeeService(@Qualifier("staffRepositoryImpl1") EmployeeRepository empRepo) {
//		super();
//		this.empRepo = empRepo;
//	}
//	setter injection
//	@Autowired
//	public void setEmpRepo(EmployeeRepository empRepo) {
//		this.empRepo = empRepo;
//	}
	
}
