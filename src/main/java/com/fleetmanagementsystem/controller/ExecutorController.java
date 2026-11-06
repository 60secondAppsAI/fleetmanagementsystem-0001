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

import com.fleetmanagementsystem.domain.Executor;
import com.fleetmanagementsystem.dto.ExecutorDTO;
import com.fleetmanagementsystem.dto.ExecutorSearchDTO;
import com.fleetmanagementsystem.dto.ExecutorPageDTO;
import com.fleetmanagementsystem.service.ExecutorService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/executor")
@RestController
public class ExecutorController {

	private final static Logger logger = LoggerFactory.getLogger(ExecutorController.class);

	@Autowired
	ExecutorService executorService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Executor> getAll() {

		List<Executor> executors = executorService.findAll();
		
		return executors;	
	}

	@GetMapping(value = "/{executorId}")
	@ResponseBody
	public ExecutorDTO getExecutor(@PathVariable Integer executorId) {
		
		return (executorService.getExecutorDTOById(executorId));
	}

 	@RequestMapping(value = "/addExecutor", method = RequestMethod.POST)
	public ResponseEntity<?> addExecutor(@RequestBody ExecutorDTO executorDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = executorService.addExecutor(executorDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/executors")
	public ResponseEntity<ExecutorPageDTO> getExecutors(ExecutorSearchDTO executorSearchDTO) {
 
		return executorService.getExecutors(executorSearchDTO);
	}	

	@RequestMapping(value = "/updateExecutor", method = RequestMethod.POST)
	public ResponseEntity<?> updateExecutor(@RequestBody ExecutorDTO executorDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = executorService.updateExecutor(executorDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
