package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseBookingDTO {

	private Integer ownerId;

	private Integer bookingId;

	private Integer bookingStatus;
	
	private Integer nextOrPrevious;
}

