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
import com.michiko.pw.dao.TaskRepository;
import com.michiko.pw.entities.Task;

@RestController
@RequestMapping("app-api/tasks")
public class TaskApiController{
	
	@Autowired
	TaskRepository taskRep;
	
	@GetMapping
	public Iterable<Task> getTasks(){
		return taskRep.findAll();
	}
	
	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable("id") Long id){
		return taskRep.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Task create(@RequestBody @Valid Task task){
		return taskRep.save(task);
		
	}
	@PutMapping(path="/{id}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Task update(@RequestBody @Valid Task task){
		return taskRep.save(task);
	}
	
	@PatchMapping(path="/{id}", consumes = "application/json")
	public Task partialUpdate(@PathVariable("id") long id, @RequestBody @Valid Task patchTask){
		Task task = taskRep.findById(id).get();
		if(patchTask.getName() != null) {
			task.setName(patchTask.getName());
		}
		if(patchTask.getDescription() != null) {
			task.setDescription(patchTask.getDescription());
		}
		if(patchTask.getStartDate() != null) {
			task.setStartDate(patchTask.getStartDate());
		}
		if(patchTask.getStartDate() != null) {
			task.setEndDate(patchTask.getEndDate());
		}
		return taskRep.save(task);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id){
		try {
		taskRep.deleteById(id);
		}
		catch(EmptyResultDataAccessException e){			
		}	
	}
	
	@GetMapping(params= {"page","size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Task> findPaginateTasks(@RequestParam("page") int page, @RequestParam("size") int size){
		Pageable pageAndSize = PageRequest.of(page, size);
		return taskRep.findAll(pageAndSize);
	}
}