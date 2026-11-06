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

import com.fleetmanagementsystem.domain.Estimate;
import com.fleetmanagementsystem.dto.EstimateDTO;
import com.fleetmanagementsystem.dto.EstimateSearchDTO;
import com.fleetmanagementsystem.dto.EstimatePageDTO;
import com.fleetmanagementsystem.service.EstimateService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/estimate")
@RestController
public class EstimateController {

	private final static Logger logger = LoggerFactory.getLogger(EstimateController.class);

	@Autowired
	EstimateService estimateService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Estimate> getAll() {

		List<Estimate> estimates = estimateService.findAll();
		
		return estimates;	
	}

	@GetMapping(value = "/{estimateId}")
	@ResponseBody
	public EstimateDTO getEstimate(@PathVariable Integer estimateId) {
		
		return (estimateService.getEstimateDTOById(estimateId));
	}

 	@RequestMapping(value = "/addEstimate", method = RequestMethod.POST)
	public ResponseEntity<?> addEstimate(@RequestBody EstimateDTO estimateDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = estimateService.addEstimate(estimateDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/estimates")
	public ResponseEntity<EstimatePageDTO> getEstimates(EstimateSearchDTO estimateSearchDTO) {
 
		return estimateService.getEstimates(estimateSearchDTO);
	}	

	@RequestMapping(value = "/updateEstimate", method = RequestMethod.POST)
	public ResponseEntity<?> updateEstimate(@RequestBody EstimateDTO estimateDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = estimateService.updateEstimate(estimateDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
