package com.michiko.pw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.michiko.pw.dao.EmployeeRepository;
import com.michiko.pw.dao.ProjectRepository;
import com.michiko.pw.dao.TaskRepository;
import com.michiko.pw.dto.ProjectTask;
import com.michiko.pw.dto.ProjectTaskCount;
import com.michiko.pw.dto.TimeChartData;
import com.michiko.pw.entities.Project;
import com.michiko.pw.entities.Task;

@Controller
public class DashboardController {

	@Autowired
	ProjectRepository proRep;
	
	@Autowired
	TaskRepository taskRep;
	
	@Autowired
	EmployeeRepository empRep;

	@GetMapping("/dashboard")
	public String displayHome(Model model) throws JsonProcessingException{
										
		List<Project> projects = proRep.findAll(); // proRepo.findAll is actually Iterable, so overwrite the function.
		model.addAttribute("projects", projects);
		
		List<Task> tasks = taskRep.findAll();
		model.addAttribute("tasks", tasks);
				
		List<ProjectTaskCount> projectTaskCount = proRep.taskCount();
		model.addAttribute("projectTaskCount", projectTaskCount);						
						
		List<TimeChartData> timelineData=proRep.getTimeData();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);

		model.addAttribute("projectTimeList", jsonTimelineString);
		
		List<ProjectTask> projectTask = proRep.proGetTask();
		model.addAttribute("projectTask", projectTask);
		
		return "main/dashboard";
	}		
}