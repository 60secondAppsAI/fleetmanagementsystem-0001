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

import com.fleetmanagementsystem.domain.Duty;
import com.fleetmanagementsystem.dto.DutyDTO;
import com.fleetmanagementsystem.dto.DutySearchDTO;
import com.fleetmanagementsystem.dto.DutyPageDTO;
import com.fleetmanagementsystem.service.DutyService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/duty")
@RestController
public class DutyController {

	private final static Logger logger = LoggerFactory.getLogger(DutyController.class);

	@Autowired
	DutyService dutyService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Duty> getAll() {

		List<Duty> dutys = dutyService.findAll();
		
		return dutys;	
	}

	@GetMapping(value = "/{dutyId}")
	@ResponseBody
	public DutyDTO getDuty(@PathVariable Integer dutyId) {
		
		return (dutyService.getDutyDTOById(dutyId));
	}

 	@RequestMapping(value = "/addDuty", method = RequestMethod.POST)
	public ResponseEntity<?> addDuty(@RequestBody DutyDTO dutyDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dutyService.addDuty(dutyDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/dutys")
	public ResponseEntity<DutyPageDTO> getDutys(DutySearchDTO dutySearchDTO) {
 
		return dutyService.getDutys(dutySearchDTO);
	}	

	@RequestMapping(value = "/updateDuty", method = RequestMethod.POST)
	public ResponseEntity<?> updateDuty(@RequestBody DutyDTO dutyDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dutyService.updateDuty(dutyDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
