package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseSubmitterDTO {

	private Integer ownerId;

	private Integer submitterId;

	private Integer submitterStatus;
	
	private Integer nextOrPrevious;
}

