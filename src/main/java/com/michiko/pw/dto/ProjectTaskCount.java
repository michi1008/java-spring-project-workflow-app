package com.michiko.pw.dto;

import java.util.Date;

public interface ProjectTaskCount{
	
	public long getProjectId();
	public String getProjectName();  
	public int getTaskCount();
	public Date getStartDate();
	public Date getEndDate();
	public String getDescription();
}
