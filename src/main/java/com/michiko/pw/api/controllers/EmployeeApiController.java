package com.michiko.pw.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.michiko.pw.dao.EmployeeRepository;
import com.michiko.pw.entities.Employee;

@RestController
@RequestMapping("app-api/employees")
public class EmployeeApiController{
	
	@Autowired
	EmployeeRepository empRep;
	
	@GetMapping
	public Iterable<Employee> getEmployees(){
		return empRep.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id){
		return empRep.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody @Valid Employee employee){
		return empRep.save(employee);
		
	}
	@PutMapping(path="/{id}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@RequestBody @Valid Employee employee){
		return empRep.save(employee);
	}
	
	@PatchMapping(path="/{id}", consumes = "application/json")
	public Employee partialUpdate(@PathVariable("id") long id, @RequestBody @Valid Employee patchEmployee){
		Employee emp = empRep.findById(id).get();
		if(patchEmployee.getEmail() != null) {
			emp.setEmail(patchEmployee.getEmail());
		}
		if(patchEmployee.getFirstName() != null) {
			emp.setEmail(patchEmployee.getEmail());
		}
		if(patchEmployee.getLastName() != null) {
			emp.setEmail(patchEmployee.getEmail());
		}
		return empRep.save(emp);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id){
		try {
		empRep.deleteById(id);
		}
		catch(EmptyResultDataAccessException e){
			
		}
	}
	
	@GetMapping(params= {"page","size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Employee> findPaginateEmployees(@RequestParam("page") int page, @RequestParam("size") int size){
		Pageable pageAndSize = PageRequest.of(page, size);
		return empRep.findAll(pageAndSize);
	}
}
