package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseDutyDTO {

	private Integer ownerId;

	private Integer dutyId;

	private Integer dutyStatus;
	
	private Integer nextOrPrevious;
}

