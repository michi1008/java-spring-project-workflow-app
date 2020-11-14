package com.michiko.pw.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  //exchange data between view and controller
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.michiko.pw.dto.ProjectTask;
import com.michiko.pw.dto.ProjectTaskEmployee;
import com.michiko.pw.dto.TimeChartData;
import com.michiko.pw.entities.Project;
import com.michiko.pw.entities.Task;
import com.michiko.pw.services.EmployeeService;
import com.michiko.pw.services.ProjectService;
import com.michiko.pw.services.TaskService;

@Controller
@RequestMapping("/projects")
public class ProjectController{
	
	@Autowired //spring framework creates instance automatically
	ProjectService proService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	EmployeeService empService;
		
	@GetMapping
	public String displayProjects(Model model){
		
		List<Project> projects = proService.getAll();		
		model.addAttribute("projects", projects);	
		
		return "projects/project-list";
	}
	
	@GetMapping("/new")
	public String displayProjetForm(Model model){
		
		Project project = new Project();   // to need to create an empty instance using the empty constructor
		List<Task> tasks = taskService.getAll();
		model.addAttribute("project", project); //"project" --> HTML th:object="${project}"
		model.addAttribute("allTasks", tasks);   //"allTasks" --> HTML th:each = "task : ${allTasks}" 

		return "projects/project-form";				

	}
	
	@GetMapping("/update")
	public String updateProjectForm(@RequestParam("id") long id, Model model){ //"id" should be the same as id=${aProject.projectId})
		
		Project theProject = proService.findByProjectId(id);
		List<Task> tasks = taskService.getAll();
		model.addAttribute("project", theProject);
		model.addAttribute("allTasks", tasks);
		
		return "projects/project-form";		
	}
	
	@GetMapping("/delete")
	public String deleteEmployeeForm(@RequestParam("id") long id, Model model){
		
		Project theProject = proService.findByProjectId(id);		
		proService.delete(theProject);		
		return "redirect:/projects";
	}
	
	@PostMapping("/save")	
	public String saveProjet(@ModelAttribute("task-project") @Valid Project project, Model model, BindingResult result){
		
		if(result.hasErrors())
			return "projects/project-form";

		proService.save(project);
						
		return "redirect:/projects";  //when you save, it is better to redirect to prevent duplicate submission
	}
	
	@GetMapping("/")
	public String displayProjectTimeLines(Model model) throws JsonProcessingException{
		
		List<TimeChartData> timelineData=proService.getTimeData();			
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);		
		model.addAttribute("projectTimeList", jsonTimelineString);

		return "/";
	}
	
	@GetMapping("/projectDetails")	
	public String displayProjectDetails(@RequestParam("id") long id, Model model) throws JsonProcessingException {
		
		List<ProjectTask> projectTasks = proService.findTaskByProjectId(id);	
		model.addAttribute("projectTasks", projectTasks);
		
		List<ProjectTaskEmployee> projectTaskEmployees = taskService.findEmployeeByProjectId(id);
		model.addAttribute("projectTaskEmployees", projectTaskEmployees);
										
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(projectTasks);
		model.addAttribute("taskTimeList", jsonTimelineString);
		
		return "projects/projectDetails";
	}		
}