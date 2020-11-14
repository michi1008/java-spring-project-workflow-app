package com.michiko.pw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.michiko.pw.dto.ProjectTaskEmployee;
import com.michiko.pw.dto.TaskEmployee;
import com.michiko.pw.entities.Task;

@RepositoryRestResource(collectionResourceRel="apitasks", path="apitasks")
public interface TaskRepository extends PagingAndSortingRepository<Task, Long>{
										
	Task findByTaskId(long id);
	
	List<Task> findAll();

	@Query(nativeQuery=true, value="select e.first_name as firstName, e.last_name as lastName, e.email as email, t.task_id as taskId,t.name as taskName "
			+ "FROM task t "
			+ "JOIN task_employee te "
			+ "ON t.task_id = te.task_id "
			+ "JOIN employee e "
			+ "ON e.employee_id = te.employee_id AND te.task_id=?")	
	public List<TaskEmployee> findEmployeeByTaskId(long id);
	
	@Query(nativeQuery=true, value="select e.first_name as firstName, e.last_name as lastName, e.email as email, t.task_id as taskId, t.name as taskName, p.project_id as projectId, p.name as projectName "
			+ "FROM project p "
			+ "JOIN project_task pt "
			+ "ON p.project_id = pt.project_id "
			+ "JOIN task t "
			+ "ON pt.task_id = t.task_id "
			+ "JOIN task_employee te "
			+ "ON t.task_id=te.task_id "
			+ "JOIN employee e "
			+ "ON te.employee_id = e.employee_id "
			+ "AND p.project_id=? "
			+ "ORDER By te.task_id")
	public List<ProjectTaskEmployee> findEmployeeByProjectId(long id);
}