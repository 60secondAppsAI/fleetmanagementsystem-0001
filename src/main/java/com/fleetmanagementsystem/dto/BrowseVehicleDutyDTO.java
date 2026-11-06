package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseVehicleDutyDTO {

	private Integer ownerId;

	private Integer vehicleDutyId;

	private Integer vehicleDutyStatus;
	
	private Integer nextOrPrevious;
}

