package com.michiko.pw.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.michiko.pw.dao.EmployeeRepository;
import com.michiko.pw.dao.TaskRepository;
import com.michiko.pw.dto.EmployeeTaskCount;
import com.michiko.pw.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController{
	
	@Autowired
	EmployeeRepository empService;
	
	@Autowired
	TaskRepository taskService;
	
	@GetMapping
	public String displayEmployees(Model model) throws JsonProcessingException{
		
		List<Employee> employees = empService.findAll();
		model.addAttribute("employees", employees);	
		
		List<EmployeeTaskCount> employeeTaskCount = empService.taskCount();
		model.addAttribute("empTaskCount", employeeTaskCount);
			
		// Convert taskData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(employeeTaskCount);
		// [["Not Started", 1], ["Work in process", 2], ["Completed", 1]]
		
		model.addAttribute("taskCount", jsonString);
		
		return "employees/employee-list";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model){
		
		Employee employee = new Employee();		
		model.addAttribute("employee", employee);
		
		return "employees/employee-form";
	
	}
	
	@GetMapping("/update")
	public String updateEmployeeForm(@RequestParam("id") long id, Model model){ //"id" should be the same as id=${anEmployee.employeeId})
		
		Employee theEmployee = empService.findByEmployeeId(id);
		model.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteEmployeeForm(@RequestParam("id") long id, Model model){
		Employee theEmployee = empService.findByEmployeeId(id);
		empService.delete(theEmployee);
		
		return "redirect:/employees";
	}
	
	@PostMapping("/save")
	public String saveEmployee(Model model, @Valid Employee employee, BindingResult result){	
		
		if(result.hasErrors())
			return "employees/employee-form";
		
		empService.save(employee);
		
		return "redirect:/employees";  //url endpoint
	}		
}