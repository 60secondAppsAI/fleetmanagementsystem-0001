package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseDriverDutyDTO {

	private Integer ownerId;

	private Integer driverDutyId;

	private Integer driverDutyStatus;
	
	private Integer nextOrPrevious;
}

