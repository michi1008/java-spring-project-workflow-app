package com.michiko.pw.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Project{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="project_generator")
	@SequenceGenerator(name = "project_generator", sequenceName = "project_seq", allocationSize = 1)
	private long projectId;
	
	@NotBlank(message = "Must give a project name")
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
				joinColumns = @JoinColumn(name = "project_id"),
				inverseJoinColumns = @JoinColumn(name = "task_id"))
	@JsonIgnore
	private List<Task> tasks;
			
	public long getProjectId(){
		return projectId;
	}

	public void setProjectId(long projectId){
		this.projectId = projectId;
	}
		
	public List<Task> getTasks(){
		return tasks;
	}

	public void setTasks(List<Task> tasks){
		this.tasks = tasks;
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

	public void setDescription(String description){
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

	public Project(String name, String description, Date startDate, Date endDate){
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Project(){
		super();
	}
	
	public void addTask(Task task){
		if(tasks == null) {
			tasks = new ArrayList<>();			
		}
		tasks.add(task);
	}

	public Project(String name, String description){
		super();
		this.name = name;
		this.description = description;
	}
}
