package com.fleetmanagementsystem.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;


import com.fleetmanagementsystem.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.fleetmanagementsystem.domain.Journey;
import com.fleetmanagementsystem.dto.JourneyDTO;
import com.fleetmanagementsystem.dto.JourneySearchDTO;
import com.fleetmanagementsystem.dto.JourneyPageDTO;
import com.fleetmanagementsystem.service.JourneyService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/journey")
@RestController
public class JourneyController {

	private final static Logger logger = LoggerFactory.getLogger(JourneyController.class);

	@Autowired
	JourneyService journeyService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Journey> getAll() {

		List<Journey> journeys = journeyService.findAll();
		
		return journeys;	
	}

	@GetMapping(value = "/{journeyId}")
	@ResponseBody
	public JourneyDTO getJourney(@PathVariable Integer journeyId) {
		
		return (journeyService.getJourneyDTOById(journeyId));
	}

 	@RequestMapping(value = "/addJourney", method = RequestMethod.POST)
	public ResponseEntity<?> addJourney(@RequestBody JourneyDTO journeyDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = journeyService.addJourney(journeyDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/journeys")
	public ResponseEntity<JourneyPageDTO> getJourneys(JourneySearchDTO journeySearchDTO) {
 
		return journeyService.getJourneys(journeySearchDTO);
	}	

	@RequestMapping(value = "/updateJourney", method = RequestMethod.POST)
	public ResponseEntity<?> updateJourney(@RequestBody JourneyDTO journeyDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = journeyService.updateJourney(journeyDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
