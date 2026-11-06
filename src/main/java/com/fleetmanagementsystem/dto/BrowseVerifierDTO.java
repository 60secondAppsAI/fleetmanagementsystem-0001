package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseVerifierDTO {

	private Integer ownerId;

	private Integer verifierId;

	private Integer verifierStatus;
	
	private Integer nextOrPrevious;
}

