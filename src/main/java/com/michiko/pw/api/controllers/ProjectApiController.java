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
import com.michiko.pw.dao.ProjectRepository;
import com.michiko.pw.entities.Project;

@RestController
@RequestMapping("app-api/projects")
public class ProjectApiController{
	
	@Autowired
	ProjectRepository proRep;
	
	@GetMapping
	public Iterable<Project> getProjects(){
		return proRep.findAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id){
		return proRep.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody @Valid Project project){
		return proRep.save(project);
		
	}
	@PutMapping(path="/{id}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody @Valid Project project){
		return proRep.save(project);
	}
	
	@PatchMapping(path="/{id}", consumes = "application/json")
	public Project partialUpdate(@PathVariable("id") long id, @RequestBody @Valid Project patchProject){
		Project pro = proRep.findById(id).get();
		if(patchProject.getName() != null){
			pro.setName(patchProject.getName());
		}
		if(patchProject.getDescription() != null){
			pro.setDescription(patchProject.getDescription());
		}
		if(patchProject.getStartDate() != null){
			pro.setStartDate(patchProject.getStartDate());
		}
		if(patchProject.getStartDate() != null){
			pro.setEndDate(patchProject.getEndDate());
		}
		return proRep.save(pro);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id){
		try {
		proRep.deleteById(id);
		}
		catch(EmptyResultDataAccessException e){
			
		}
	}
	@GetMapping(params= {"page","size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Project> findPaginateProjects(@RequestParam("page") int page, @RequestParam("size") int size){
		Pageable pageAndSize = PageRequest.of(page, size);
		return proRep.findAll(pageAndSize);
	}
	
}
