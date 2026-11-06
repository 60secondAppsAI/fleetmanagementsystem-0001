package com.fleetmanagementsystem.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ExecutorDTO {

	private Integer executorId;

	private Task taskId;

	private User executorUserId;

	private Date executionDate;






}
