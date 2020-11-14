package com.michiko.pw.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michiko.pw.dao.TaskRepository;
import com.michiko.pw.dto.ProjectTaskEmployee;
import com.michiko.pw.dto.TaskEmployee;
import com.michiko.pw.entities.Task;

@Service
public class TaskService{
	
	@Autowired
	TaskRepository taskRep;
	
	public Task save(Task task){
		return taskRep.save(task);
	}		
	
	public Task findByTaskId(long id){		
		return taskRep.findByTaskId(id);
	}

	public void delete(Task task){ 
		taskRep.delete(task);
	}

	public List<Task> getAll(){		
		return taskRep.findAll();
	}
	
	public List<TaskEmployee> findEmployeeByTaskId(long id){		
		return taskRep.findEmployeeByTaskId(id);
	}

	public List<ProjectTaskEmployee> findEmployeeByProjectId(long id){		
		return taskRep.findEmployeeByProjectId(id);
	}
}
	

