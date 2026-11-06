package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseExecutorDTO {

	private Integer ownerId;

	private Integer executorId;

	private Integer executorStatus;
	
	private Integer nextOrPrevious;
}

