package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseEstimateDTO {

	private Integer ownerId;

	private Integer estimateId;

	private Integer estimateStatus;
	
	private Integer nextOrPrevious;
}

