package com.michiko.pw.entities;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
	@SequenceGenerator(name = "employee_generator", sequenceName = "employee_seq", allocationSize = 1)
	private long employeeId;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinTable(name = "task_employee", 
	joinColumns = @JoinColumn(name = "employee_id"),
	inverseJoinColumns = @JoinColumn(name = "task_id"))
	@JsonIgnore
	private List<Task> tasks; 
	
	@NotBlank(message = "Must give a first name")
	@Size(min = 2, max = 50)
	private String firstName;
	
	@NotBlank(message="Must give a last name")
	@Size(min = 1, max = 50)
	private String lastName;
	
	@NotBlank(message = "Must give an email address")
	@Email(message = "Must be a valid email address")	
	private String email;
		
	public long getEmployeeId(){
		return employeeId;
	}
	
	public void setEmployeeId(long employeeId){
		this.employeeId = employeeId;
	}
		
	public List<Task> getTasks(){
		return tasks;
	}
	
	public void setTasks(List<Task> tasks){
		this.tasks = tasks;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public Employee(String firstName, String lastName, String email){
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public Employee(){
		super();
	}		
}
