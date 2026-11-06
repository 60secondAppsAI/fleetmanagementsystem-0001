package com.fleetmanagementsystem.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VerifierPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<VerifierDTO> verifiers;
}





