package com.michiko.pw.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.michiko.pw.entities.Project;
	
@SpringBootTest	
@RunWith(SpringRunner.class)
@SqlGroup({
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
	
})
	
	public class ProjectRepositoryIntegrationTest{
		@Autowired
		ProjectRepository proRepo;
		
		@Test
		public void ifNewProjectSaved_thenSuccess(){
			
			Project newProject = new Project("New Test Project", "Test Desription");
			proRepo.save(newProject);
			assertEquals(5, proRepo.findAll().size());
		}		
	}

