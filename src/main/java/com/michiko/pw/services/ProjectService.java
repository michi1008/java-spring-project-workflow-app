package com.michiko.pw.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michiko.pw.dao.ProjectRepository;
import com.michiko.pw.dto.ProjectTask;
import com.michiko.pw.dto.ProjectTaskCount;
import com.michiko.pw.dto.TimeChartData;
import com.michiko.pw.entities.Project;

@Service
public class ProjectService{
	
	@Autowired
	ProjectRepository proRep;
		
	public Project save(Project project){
		return proRep.save(project);
	}

	public List<Project> getAll(){
		return proRep.findAll();
	}
		
	public void delete(Project theProject){
		proRep.delete(theProject);
	}
	
	public List<TimeChartData> getTimeData(){
		return proRep.getTimeData();
	}
	
	public List<ProjectTask> getProjectTask(){
		return proRep.proGetTask();
	}

	public List<ProjectTaskCount> taskCount(){		
		return proRep.taskCount();
	}

	public Project findByProjectId(long id){		
		return proRep.findByProjectId(id);
	}
	
	public List<ProjectTask>findTaskByProjectId(long id){			
		return proRep.findTaskByProjectId(id);
	}
}
	

	



	



