package com.example.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.pma.dto.ChartData;
import com.example.pma.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project,Long> {

	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery=true,value="select p.stage as status,Count(p.stage) as statusCount from project p group by p.stage")
	public List<ChartData> getStatus();
	
}
