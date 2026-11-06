package com.fleetmanagementsystem.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExecutorSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer executorId;
	
	private Task taskId;
	
	private User executorUserId;
	
	private Date executionDate;
	
}
