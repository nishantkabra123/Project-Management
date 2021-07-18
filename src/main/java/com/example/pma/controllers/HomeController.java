package com.example.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.dao.ProjectRepository;
import com.example.pma.dto.ChartData;
import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNo",ver);
		
		List<Project> projects= proRepo.findAll();
		List<ChartData> projectData= proRepo.getStatus();
		Map<String,Object> map=new HashMap<>();
		//lets convert project data object to a json string structure
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonString=objectMapper.writeValueAsString(projectData);
		//[["NOTSTARTED,1"],["INPROGRESS,2"],["COMPLETED,1"]]
		model.addAttribute("projectStatusCount",jsonString);
		model.addAttribute("projects",projects);
//		List<Employee> emps= empRepo.findAll();
		List<EmployeeProject> empProjCount= empRepo.employeeProjects();
//		model.addAttribute("emps",emps);
		model.addAttribute("empProjCount",empProjCount);
		return "main/home";
	}

}
