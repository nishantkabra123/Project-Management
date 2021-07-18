package com.example.pma.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.entities.Employee;

@RestController
@RequestMapping("/api/employees")
public class EmployeeApiController {

	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	@ResponseBody
	public List<Employee> getEmployees(){
		return empRepo.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return empRepo.findById(id).get();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Employee create(@RequestBody @Valid Employee emp) {
		return empRepo.save(emp);		
	}	
		
	@PutMapping(path="/{id}",consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Employee update(@RequestBody @Valid Employee employee) {
		return empRepo.save(employee);
	}
	
	@PatchMapping(path="/{id}",consumes="application/json")
	public Employee partialUpdate(@PathVariable("id") long id,@RequestBody @Valid Employee patchEmp) {
		Employee emp=empRepo.findById(id).get();
		
		if(patchEmp.getFirstName()!=null) {
			emp.setFirstName(patchEmp.getFirstName());
		}
		if(patchEmp.getLastName()!=null) {
			emp.setLastName(patchEmp.getLastName());
		}
		if(patchEmp.getEmail()!=null) {
			emp.setEmail(patchEmp.getEmail());
		}
		
		return empRepo.save(emp);
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		try{
			empRepo.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			
		}
	}
	
	//we can take another param to sort by id,name etc
		@GetMapping(params= {"page","size","sortBy"})
		@ResponseStatus(HttpStatus.OK)
		public Iterable<Employee> findPaginatedEmployee(
		@RequestParam("page") int page,@RequestParam("size") int size,
		@RequestParam("sortBy") String sortBy){
		Pageable pageAndSize=PageRequest.of(page,size,Sort.by(sortBy).descending());
		return empRepo.findAll(pageAndSize);
	}	
	
}
