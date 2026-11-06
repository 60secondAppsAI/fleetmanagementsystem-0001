package com.fleetmanagementsystem.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class SubmitterDTO {

	private Integer submitterId;

	private Task taskId;

	private User userId;

	private Date submissionDate;






}
