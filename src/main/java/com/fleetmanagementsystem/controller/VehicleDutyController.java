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

import com.fleetmanagementsystem.domain.VehicleDuty;
import com.fleetmanagementsystem.dto.VehicleDutyDTO;
import com.fleetmanagementsystem.dto.VehicleDutySearchDTO;
import com.fleetmanagementsystem.dto.VehicleDutyPageDTO;
import com.fleetmanagementsystem.service.VehicleDutyService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/vehicleDuty")
@RestController
public class VehicleDutyController {

	private final static Logger logger = LoggerFactory.getLogger(VehicleDutyController.class);

	@Autowired
	VehicleDutyService vehicleDutyService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<VehicleDuty> getAll() {

		List<VehicleDuty> vehicleDutys = vehicleDutyService.findAll();
		
		return vehicleDutys;	
	}

	@GetMapping(value = "/{vehicleDutyId}")
	@ResponseBody
	public VehicleDutyDTO getVehicleDuty(@PathVariable Integer vehicleDutyId) {
		
		return (vehicleDutyService.getVehicleDutyDTOById(vehicleDutyId));
	}

 	@RequestMapping(value = "/addVehicleDuty", method = RequestMethod.POST)
	public ResponseEntity<?> addVehicleDuty(@RequestBody VehicleDutyDTO vehicleDutyDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = vehicleDutyService.addVehicleDuty(vehicleDutyDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/vehicleDutys")
	public ResponseEntity<VehicleDutyPageDTO> getVehicleDutys(VehicleDutySearchDTO vehicleDutySearchDTO) {
 
		return vehicleDutyService.getVehicleDutys(vehicleDutySearchDTO);
	}	

	@RequestMapping(value = "/updateVehicleDuty", method = RequestMethod.POST)
	public ResponseEntity<?> updateVehicleDuty(@RequestBody VehicleDutyDTO vehicleDutyDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = vehicleDutyService.updateVehicleDuty(vehicleDutyDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
