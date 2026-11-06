package com.fleetmanagementsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.fleetmanagementsystem.domain.Executor;
import com.fleetmanagementsystem.dto.ExecutorDTO;
import com.fleetmanagementsystem.dto.ExecutorSearchDTO;
import com.fleetmanagementsystem.dto.ExecutorPageDTO;
import com.fleetmanagementsystem.dto.ExecutorConvertCriteriaDTO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ExecutorService extends GenericService<Executor, Integer> {

	List<Executor> findAll();

	ResultDTO addExecutor(ExecutorDTO executorDTO, RequestDTO requestDTO);

	ResultDTO updateExecutor(ExecutorDTO executorDTO, RequestDTO requestDTO);

    Page<Executor> getAllExecutors(Pageable pageable);

    Page<Executor> getAllExecutors(Specification<Executor> spec, Pageable pageable);

	ResponseEntity<ExecutorPageDTO> getExecutors(ExecutorSearchDTO executorSearchDTO);
	
	List<ExecutorDTO> convertExecutorsToExecutorDTOs(List<Executor> executors, ExecutorConvertCriteriaDTO convertCriteria);

	ExecutorDTO getExecutorDTOById(Integer executorId);



	
}
