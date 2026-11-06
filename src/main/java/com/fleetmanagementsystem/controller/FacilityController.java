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

import com.fleetmanagementsystem.domain.Facility;
import com.fleetmanagementsystem.dto.FacilityDTO;
import com.fleetmanagementsystem.dto.FacilitySearchDTO;
import com.fleetmanagementsystem.dto.FacilityPageDTO;
import com.fleetmanagementsystem.service.FacilityService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/facility")
@RestController
public class FacilityController {

	private final static Logger logger = LoggerFactory.getLogger(FacilityController.class);

	@Autowired
	FacilityService facilityService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Facility> getAll() {

		List<Facility> facilitys = facilityService.findAll();
		
		return facilitys;	
	}

	@GetMapping(value = "/{facilityId}")
	@ResponseBody
	public FacilityDTO getFacility(@PathVariable Integer facilityId) {
		
		return (facilityService.getFacilityDTOById(facilityId));
	}

 	@RequestMapping(value = "/addFacility", method = RequestMethod.POST)
	public ResponseEntity<?> addFacility(@RequestBody FacilityDTO facilityDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = facilityService.addFacility(facilityDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/facilitys")
	public ResponseEntity<FacilityPageDTO> getFacilitys(FacilitySearchDTO facilitySearchDTO) {
 
		return facilityService.getFacilitys(facilitySearchDTO);
	}	

	@RequestMapping(value = "/updateFacility", method = RequestMethod.POST)
	public ResponseEntity<?> updateFacility(@RequestBody FacilityDTO facilityDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = facilityService.updateFacility(facilityDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
