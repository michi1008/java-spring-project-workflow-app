package com.michiko.pw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.michiko.pw.dao.EmployeeRepository;
import com.michiko.pw.dao.ProjectRepository;
import com.michiko.pw.dao.TaskRepository;

@Controller
public class HomeController{
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	ProjectRepository proRep;
	
	@Autowired
	TaskRepository taskRep;
	
	@Autowired
	EmployeeRepository empRep;
	@GetMapping("/")
	public String DisplayHomePage(Model model){
		
		model.addAttribute("version", ver);
		
		return "main/home";
		
	}
}
