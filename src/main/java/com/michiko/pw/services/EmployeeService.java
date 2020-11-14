package com.michiko.pw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michiko.pw.dao.EmployeeRepository;
import com.michiko.pw.entities.Employee;

@Service
public class EmployeeService{
	
	@Autowired
	EmployeeRepository empRep;
		
	public Employee save(Employee employee){
		return empRep.save(employee);
	}

	public Iterable<Employee> getAll(){
		return empRep.findAll();
	}
		
	public Employee findByEmployeeId(long id){		
		return empRep.findByEmployeeId(id);
	}

	public void delete(Employee employee){
		empRep.delete(employee);
	}			
}