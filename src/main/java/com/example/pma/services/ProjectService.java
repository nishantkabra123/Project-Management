package com.example.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pma.dao.ProjectRepository;
import com.example.pma.dto.ChartData;
import com.example.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository proRepo;
	
	public Project save(Project proj) {
		return proRepo.save(proj);
	}
	
	public List<Project> getAll(){
		return proRepo.findAll();
	}
	
	public List<ChartData> getStatus(){
		return proRepo.getStatus();
	}
}
