package com.michiko.pw.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.michiko.pw.dto.ProjectTask;
import com.michiko.pw.dto.ProjectTaskCount;
import com.michiko.pw.dto.TimeChartData;
import com.michiko.pw.entities.Project;

@RepositoryRestResource(collectionResourceRel="apiprojects", path="apiprojects")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{
	
	@Override
	List<Project> findAll();
	
	@Query(nativeQuery=true, value="SELECT p.project_id as projectId, p.name as projectName, start_date as startDate, end_date as endDate, COUNT(pt.task_id) as taskCount "
			+ "FROM project p left join project_task pt ON pt.project_id=p.project_id "
			+ "GROUP BY p.project_id, p.name, p.start_date, p.end_date")
	public List<ProjectTaskCount> taskCount();

	Project findByProjectId(long id);
	
	@Query(nativeQuery=true, value="SELECT name as projectName, start_date as startDate, end_date as endDate "
			+ "FROM project WHERE start_date is not NULL")
	public List<TimeChartData>getTimeData();
	
	@Query(nativeQuery=true, value="SELECT p.project_id as projectId, p.name as projectName, t.name as taskName, t.start_date as startDate, t.end_date as endDate, t.description as description "
			+ "FROM (project_task pt JOIN project p ON p.project_id = pt.project_id "
			+ "JOIN task t ON pt.task_id=t.task_id)")
	public List<ProjectTask> proGetTask();
	
	@Query(nativeQuery=true, value="SELECT p.project_id as projectId, p.name as projectName, t.name as taskName, t.start_date as startDate, t.end_date as endDate, t.description as description "
			+ "FROM (project_task pt JOIN project p ON p.project_id = pt.project_id "
			+ "JOIN task t ON pt.task_id=t.task_id "
			+ "AND p.project_id=?)")
	public List<ProjectTask> findTaskByProjectId(long id);			
}
