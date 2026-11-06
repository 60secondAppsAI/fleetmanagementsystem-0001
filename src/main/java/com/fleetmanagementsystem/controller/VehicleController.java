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

import com.fleetmanagementsystem.domain.Vehicle;
import com.fleetmanagementsystem.dto.VehicleDTO;
import com.fleetmanagementsystem.dto.VehicleSearchDTO;
import com.fleetmanagementsystem.dto.VehiclePageDTO;
import com.fleetmanagementsystem.service.VehicleService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/vehicle")
@RestController
public class VehicleController {

	private final static Logger logger = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
	VehicleService vehicleService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Vehicle> getAll() {

		List<Vehicle> vehicles = vehicleService.findAll();
		
		return vehicles;	
	}

	@GetMapping(value = "/{vehicleId}")
	@ResponseBody
	public VehicleDTO getVehicle(@PathVariable Integer vehicleId) {
		
		return (vehicleService.getVehicleDTOById(vehicleId));
	}

 	@RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
	public ResponseEntity<?> addVehicle(@RequestBody VehicleDTO vehicleDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = vehicleService.addVehicle(vehicleDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/vehicles")
	public ResponseEntity<VehiclePageDTO> getVehicles(VehicleSearchDTO vehicleSearchDTO) {
 
		return vehicleService.getVehicles(vehicleSearchDTO);
	}	

	@RequestMapping(value = "/updateVehicle", method = RequestMethod.POST)
	public ResponseEntity<?> updateVehicle(@RequestBody VehicleDTO vehicleDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = vehicleService.updateVehicle(vehicleDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
