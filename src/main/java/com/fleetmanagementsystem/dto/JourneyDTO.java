package com.fleetmanagementsystem.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class JourneyDTO {

	private Integer journeyId;

	private String startPoint;

	private String endPoint;

	private double distanceTraveled;

	private double fare;






}
