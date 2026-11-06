package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseJourneyDTO {

	private Integer ownerId;

	private Integer journeyId;

	private Integer journeyStatus;
	
	private Integer nextOrPrevious;
}

