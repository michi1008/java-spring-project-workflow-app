package com.michiko.pw.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
	@SequenceGenerator(name = "task_generator", sequenceName = "task_seq", allocationSize = 1)
	private long taskId;
	
	@Column(nullable = false,unique = true)
	@NotBlank(message = "Must give a task name")
	@Size(min = 2, max = 100)
	private String name;
	
	@Size(max = 300, message = "Must be within 300 characters")
	private String description;	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
						fetch = FetchType.LAZY)
	@JoinTable(name = "project_task", 
				joinColumns = @JoinColumn(name = "task_id"),
				inverseJoinColumns = @JoinColumn(name = "project_id"))
	@JsonIgnore
	private List<Project> projects; 

	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinTable(name = "task_employee", 
	joinColumns = @JoinColumn(name = "task_id"),
	inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private List<Employee> employees;
	
	public long getTaskId(){
		return taskId;
	}

	public void setTaskId(long taskId){
		this.taskId = taskId;
	}	
		
	public List<Employee> getEmployees(){
		return employees;
	}

	public void setEmployees(List<Employee> employees){
		this.employees = employees;
	}

	public List<Project> getProjects(){
		return projects;
	}

	public void setProjects(List<Project> projects){
		this.projects = projects;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getDescription(){
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate(){
		return startDate;
	}

	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}

	public Date getEndDate(){
		return endDate;
	}

	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}

	public Task(String name, String description, Date startDate, Date endDate){
		super();
		this.name = name;		
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Task(){
		super();
	}
	
	public void addEmployee(Employee emp){
		if(employees == null) {
			employees = new ArrayList<>();			
		}
		employees.add(emp);
	}
}
