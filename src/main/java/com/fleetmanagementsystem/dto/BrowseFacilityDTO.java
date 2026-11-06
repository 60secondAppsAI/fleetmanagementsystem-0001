package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseFacilityDTO {

	private Integer ownerId;

	private Integer facilityId;

	private Integer facilityStatus;
	
	private Integer nextOrPrevious;
}

