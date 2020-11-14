package com.michiko.pw.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public interface TimeChartData{
	
	public String getProjectName();
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getStartDate();
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getEndDate();	
}
