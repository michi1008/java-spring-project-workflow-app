package com.michiko.pw.dto;

import java.util.Date;

public interface ProjectTask{
	
	public long getProjectId();	
	public String getProjectName();
	public String getTaskName();
	public Date getStartDate();
	public Date getEndDate();
	public String getDescription();
	public String getFirstName();
	public String getLastName();			
}
