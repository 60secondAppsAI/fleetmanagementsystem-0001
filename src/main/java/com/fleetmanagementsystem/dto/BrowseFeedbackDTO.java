package com.fleetmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseFeedbackDTO {

	private Integer ownerId;

	private Integer feedbackId;

	private Integer feedbackStatus;
	
	private Integer nextOrPrevious;
}

