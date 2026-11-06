package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseDriverDTO {

	private Integer ownerId;

	private Integer driverId;

	private Integer driverStatus;
	
	private Integer nextOrPrevious;
}

