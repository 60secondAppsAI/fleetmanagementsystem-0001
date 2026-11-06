package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseVehicleDTO {

	private Integer ownerId;

	private Integer vehicleId;

	private Integer vehicleStatus;
	
	private Integer nextOrPrevious;
}

