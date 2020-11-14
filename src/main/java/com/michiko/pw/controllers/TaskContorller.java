package com.michiko.pw.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.michiko.pw.dao.EmployeeRepository;
import com.michiko.pw.dao.TaskRepository;
import com.michiko.pw.dto.TaskEmployee;
import com.michiko.pw.entities.Employee;
import com.michiko.pw.entities.Task;

@Controller
@RequestMapping("/tasks")
public class TaskContorller {
		
	@Autowired
	TaskRepository taskService;
	
	@Autowired
	EmployeeRepository empService;
	
	@GetMapping
	public String displayProjects(Model model) throws JsonProcessingException{
		
		List<Task> tasks=taskService.findAll();
		model.addAttribute("tasks",tasks);
		
		return "tasks/task-list";				
		
	}
	
	@GetMapping("/new")
	public String displayTaskForm(Model model){
		
		Task aTask = new Task();   
		List<Employee> employees = empService.findAll();
		model.addAttribute("task", aTask);
		model.addAttribute("allEmployees", employees);
		
		return "tasks/task-form";

	}
	
	@GetMapping("/update")
	public String updateTaskForm(@RequestParam("id") long id, Model model) { //"id" should be the same as id=${anEmployee.employeeId})
		
		Task task = taskService.findByTaskId(id);
		List<Employee> employees = empService.findAll();
		model.addAttribute("task", task);
		model.addAttribute("allEmployees", employees);
		
		return "tasks/task-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteEmployeeForm(@RequestParam("id") long id, Model model) {
		Task theTask = taskService.findByTaskId(id);
		taskService.delete(theTask);
		
		return "redirect:/tasks";
	}
	
	@PostMapping("/save")	
	public String saveTask(@Valid @ModelAttribute("task-form") Task task, Model model, BindingResult result){
		
		if(result.hasErrors()) {
			
        	return "tasks/task-form";  
		}			
		taskService.save(task);
		
		return "redirect:/tasks";  
	}
	
	@GetMapping("/assignedEmployees")
	public String displayAssignedEmployee(@RequestParam("id") long id, Model model) {
		
		List<TaskEmployee> taskEmployees = taskService.findEmployeeByTaskId(id);
		model.addAttribute("taskEmployees", taskEmployees);
		
		for(int i=0; i<taskEmployees.size(); i++)
			System.out.println(taskEmployees.get(i));
		
		return "tasks/assignedEmployees";
	}		
}