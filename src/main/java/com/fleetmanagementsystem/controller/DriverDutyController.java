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

import com.fleetmanagementsystem.domain.DriverDuty;
import com.fleetmanagementsystem.dto.DriverDutyDTO;
import com.fleetmanagementsystem.dto.DriverDutySearchDTO;
import com.fleetmanagementsystem.dto.DriverDutyPageDTO;
import com.fleetmanagementsystem.service.DriverDutyService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/driverDuty")
@RestController
public class DriverDutyController {

	private final static Logger logger = LoggerFactory.getLogger(DriverDutyController.class);

	@Autowired
	DriverDutyService driverDutyService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<DriverDuty> getAll() {

		List<DriverDuty> driverDutys = driverDutyService.findAll();
		
		return driverDutys;	
	}

	@GetMapping(value = "/{driverDutyId}")
	@ResponseBody
	public DriverDutyDTO getDriverDuty(@PathVariable Integer driverDutyId) {
		
		return (driverDutyService.getDriverDutyDTOById(driverDutyId));
	}

 	@RequestMapping(value = "/addDriverDuty", method = RequestMethod.POST)
	public ResponseEntity<?> addDriverDuty(@RequestBody DriverDutyDTO driverDutyDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = driverDutyService.addDriverDuty(driverDutyDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/driverDutys")
	public ResponseEntity<DriverDutyPageDTO> getDriverDutys(DriverDutySearchDTO driverDutySearchDTO) {
 
		return driverDutyService.getDriverDutys(driverDutySearchDTO);
	}	

	@RequestMapping(value = "/updateDriverDuty", method = RequestMethod.POST)
	public ResponseEntity<?> updateDriverDuty(@RequestBody DriverDutyDTO driverDutyDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = driverDutyService.updateDriverDuty(driverDutyDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
