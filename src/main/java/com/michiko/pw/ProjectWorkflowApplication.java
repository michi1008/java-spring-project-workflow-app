package com.michiko.pw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.michiko.pw.dao.EmployeeRepository;
import com.michiko.pw.dao.ProjectRepository;
import com.michiko.pw.dao.TaskRepository;

@SpringBootApplication
@ComponentScan(basePackages= {"com.michiko"})
public class ProjectWorkflowApplication{
	
	@Autowired
	ProjectRepository proRep;
	
	@Autowired
	TaskRepository taskRep;
	
	@Autowired
	EmployeeRepository empRep;
	
	public static void main(String[] args){
		SpringApplication.run(ProjectWorkflowApplication.class, args);
	}	
}	
