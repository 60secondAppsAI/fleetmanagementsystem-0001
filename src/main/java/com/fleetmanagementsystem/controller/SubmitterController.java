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

import com.fleetmanagementsystem.domain.Submitter;
import com.fleetmanagementsystem.dto.SubmitterDTO;
import com.fleetmanagementsystem.dto.SubmitterSearchDTO;
import com.fleetmanagementsystem.dto.SubmitterPageDTO;
import com.fleetmanagementsystem.service.SubmitterService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/submitter")
@RestController
public class SubmitterController {

	private final static Logger logger = LoggerFactory.getLogger(SubmitterController.class);

	@Autowired
	SubmitterService submitterService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Submitter> getAll() {

		List<Submitter> submitters = submitterService.findAll();
		
		return submitters;	
	}

	@GetMapping(value = "/{submitterId}")
	@ResponseBody
	public SubmitterDTO getSubmitter(@PathVariable Integer submitterId) {
		
		return (submitterService.getSubmitterDTOById(submitterId));
	}

 	@RequestMapping(value = "/addSubmitter", method = RequestMethod.POST)
	public ResponseEntity<?> addSubmitter(@RequestBody SubmitterDTO submitterDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = submitterService.addSubmitter(submitterDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/submitters")
	public ResponseEntity<SubmitterPageDTO> getSubmitters(SubmitterSearchDTO submitterSearchDTO) {
 
		return submitterService.getSubmitters(submitterSearchDTO);
	}	

	@RequestMapping(value = "/updateSubmitter", method = RequestMethod.POST)
	public ResponseEntity<?> updateSubmitter(@RequestBody SubmitterDTO submitterDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = submitterService.updateSubmitter(submitterDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
