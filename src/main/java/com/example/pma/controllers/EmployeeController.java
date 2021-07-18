package com.example.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.entities.Employee;

@Controller 
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepo;
//	constructor injection	
//	public EmployeeController(EmployeeRepository empRepo) {
//		this.empRepo=empRepo;
//	}
	
	//setter injection
//	@Autowired
//	public void setEmpRepo(EmployeeRepository empRepo){
//		this.empRepo=empRepo;
//	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		model.addAttribute("employee",new Employee());
		return "employees/new-employee";		
	}
	
	@GetMapping("/delete")
	public String deleteEmp(@RequestParam("id") Long eId) {
		empRepo.deleteById(eId);
		return "redirect:/employees/list";		
	}
	
	@GetMapping("/update")
	public String updateEmp(@RequestParam("id") Long eId,Model model) {
		model.addAttribute("employee",empRepo.findById(eId));
		return "employees/new-employee";		
	}
	
	@GetMapping("/list")
	public String listEmployeeForm(Model model) {
		List<Employee> emps= (List<Employee>)empRepo.findAll();
		model.addAttribute("emps",emps);
		return "employees/list-employees";		
	}
	
	@PostMapping("/save")
	public String createEmployee(Model model,@Valid Employee employee,Errors errors) {
		if(errors.hasErrors())
			return "employees/new-employee";
		System.out.println("name is " + employee.toString());
		empRepo.save(employee);
		//whenever saving to db redirect to prevent duplicate submission
		return "redirect:/employees/new";		
	}
	
//	@GetMapping("/s")
//	public ResponseEntity<String> createEmployesse(HttpServletRequest request) {
//		String name = request.getParameter("name");
//		System.out.println("name is " + name);
//		//whenever saving to db redirect to prevent duplicate submission
//		 return ResponseEntity.ok("Hello World!");		
//	}
	
}
