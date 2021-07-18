package com.example.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.dao.ProjectRepository;
import com.example.pma.entities.Employee;
import com.example.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository repo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		model.addAttribute("project",new Project());
		List<Employee> employees=(List<Employee>)empRepo.findAll();
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";		
	}
	
	@GetMapping("/list")
	public String listProjectForm(Model model) {
		List<Project> projects= repo.findAll();
		model.addAttribute("projects",projects);
		return "projects/list-projects";		
	}
	
	@PostMapping("/save")
	public String createProject(Project project,@RequestParam List<Long> employees, Model model) {
		repo.save(project);
		return "redirect:/projects/list";
	}
	
//	@PostMapping("/save")
//	public String createProject(Project project,@RequestParam List<Long> employees, Model model) {
//		repo.save(project);
//		Iterable<Employee> chosenEmployees=empRepo.findAllById(employees);
//		for(Employee emp: chosenEmployees) {
//			emp.setTheProject(project);
//			empRepo.save(emp);
//		}
//		//use a redirect to prevent duplicate submissions if we just show some page 
//		//which takes time to load and user clicks submit multiple times so it is convention to redirect
//		return "redirect:/projects/new";
//	}
}
