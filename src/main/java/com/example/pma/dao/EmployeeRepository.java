package com.example.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Employee;

@RepositoryRestResource(collectionResourceRel = "apiemployees",path="apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
	
	@Override
	public List<Employee> findAll();
	
	//means we are giving query specific to db like h2 
	@Query(nativeQuery=true,value="select e.first_name as firstName,e.last_name as lastName,Count(pe.employee_id) as projectCount from employee e left join project_employee pe on pe.employee_id=e.employee_id group by e.first_name,e.last_name order by 3 desc")
	public List<EmployeeProject> employeeProjects();
	
	public Employee findByEmail(String value);
	//both are allowed public Employee findEmployeeByEmail(String value);
	public Employee findEmployeeByEmployeeId(long id);
}