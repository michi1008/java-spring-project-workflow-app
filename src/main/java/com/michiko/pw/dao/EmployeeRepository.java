package com.michiko.pw.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.michiko.pw.dto.EmployeeTaskCount;
import com.michiko.pw.entities.Employee;

@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{
	
	@Override
	public List<Employee>findAll();
				
	Employee findByEmployeeId(long id);
	
	@Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(te.task_id) as taskCount "
			+ "FROM employee e left join task_employee te ON e.employee_id = te.employee_id "
			+ "GROUP BY e.first_name, e.last_name")
	public List<EmployeeTaskCount> taskCount();
				
	public Employee findByEmail(String value);
}
