package com.fleetmanagementsystem.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.fleetmanagementsystem.dao.GenericDAO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.service.impl.GenericServiceImpl;
import com.fleetmanagementsystem.dao.ExecutorDAO;
import com.fleetmanagementsystem.domain.Executor;
import com.fleetmanagementsystem.dto.ExecutorDTO;
import com.fleetmanagementsystem.dto.ExecutorSearchDTO;
import com.fleetmanagementsystem.dto.ExecutorPageDTO;
import com.fleetmanagementsystem.dto.ExecutorConvertCriteriaDTO;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import com.fleetmanagementsystem.service.ExecutorService;
import com.fleetmanagementsystem.util.ControllerUtils;


@Service
public class ExecutorServiceImpl extends GenericServiceImpl<Executor, Integer> implements ExecutorService {

    private final static Logger logger = LoggerFactory.getLogger(ExecutorServiceImpl.class);

	@Autowired
	ExecutorDAO executorDao;

	

	@Override
	public GenericDAO<Executor, Integer> getDAO() {
		return (GenericDAO<Executor, Integer>) executorDao;
	}
	
	public List<Executor> findAll () {
		List<Executor> executors = executorDao.findAll();
		
		return executors;	
		
	}

	public ResultDTO addExecutor(ExecutorDTO executorDTO, RequestDTO requestDTO) {

		Executor executor = new Executor();

		executor.setExecutorId(executorDTO.getExecutorId());

		executor.setTaskId(executorDTO.getTaskId());

		executor.setExecutorUserId(executorDTO.getExecutorUserId());

		executor.setExecutionDate(executorDTO.getExecutionDate());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		executor = executorDao.save(executor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Executor> getAllExecutors(Pageable pageable) {
		return executorDao.findAll(pageable);
	}

	public Page<Executor> getAllExecutors(Specification<Executor> spec, Pageable pageable) {
		return executorDao.findAll(spec, pageable);
	}

	public ResponseEntity<ExecutorPageDTO> getExecutors(ExecutorSearchDTO executorSearchDTO) {
	
			Integer executorId = executorSearchDTO.getExecutorId(); 
   			Date executionDate = executorSearchDTO.getExecutionDate();
 			String sortBy = executorSearchDTO.getSortBy();
			String sortOrder = executorSearchDTO.getSortOrder();
			String searchQuery = executorSearchDTO.getSearchQuery();
			Integer page = executorSearchDTO.getPage();
			Integer size = executorSearchDTO.getSize();

	        Specification<Executor> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, executorId, "executorId"); 
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, executionDate, "executionDate");
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Executor> executors = this.getAllExecutors(spec, pageable);
		
		//System.out.println(String.valueOf(executors.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(executors.getTotalPages()));
		
		List<Executor> executorsList = executors.getContent();
		
		ExecutorConvertCriteriaDTO convertCriteria = new ExecutorConvertCriteriaDTO();
		List<ExecutorDTO> executorDTOs = this.convertExecutorsToExecutorDTOs(executorsList,convertCriteria);
		
		ExecutorPageDTO executorPageDTO = new ExecutorPageDTO();
		executorPageDTO.setExecutors(executorDTOs);
		executorPageDTO.setTotalElements(executors.getTotalElements());
		return ResponseEntity.ok(executorPageDTO);
	}

	public List<ExecutorDTO> convertExecutorsToExecutorDTOs(List<Executor> executors, ExecutorConvertCriteriaDTO convertCriteria) {
		
		List<ExecutorDTO> executorDTOs = new ArrayList<ExecutorDTO>();
		
		for (Executor executor : executors) {
			executorDTOs.add(convertExecutorToExecutorDTO(executor,convertCriteria));
		}
		
		return executorDTOs;

	}
	
	public ExecutorDTO convertExecutorToExecutorDTO(Executor executor, ExecutorConvertCriteriaDTO convertCriteria) {
		
		ExecutorDTO executorDTO = new ExecutorDTO();

		executorDTO.setExecutorId(executor.getExecutorId());

		executorDTO.setTaskId(executor.getTaskId());

		executorDTO.setExecutorUserId(executor.getExecutorUserId());

		executorDTO.setExecutionDate(executor.getExecutionDate());
		
		return executorDTO;
	}

	public ResultDTO updateExecutor(ExecutorDTO executorDTO, RequestDTO requestDTO) {
		
		Executor executor = executorDao.getById(executorDTO.getExecutorId());
		
		executor.setExecutorId(ControllerUtils.setValue(executor.getExecutorId(), executorDTO.getExecutorId()));
		
		executor.setTaskId(ControllerUtils.setValue(executor.getTaskId(), executorDTO.getTaskId()));
		
		executor.setExecutorUserId(ControllerUtils.setValue(executor.getExecutorUserId(), executorDTO.getExecutorUserId()));
		
		executor.setExecutionDate(ControllerUtils.setValue(executor.getExecutionDate(), executorDTO.getExecutionDate()));

        executor = executorDao.save(executor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ExecutorDTO getExecutorDTOById(Integer executorId) {
	
		Executor executor = executorDao.getById(executorId);
		
		ExecutorConvertCriteriaDTO convertCriteria = new ExecutorConvertCriteriaDTO();
		return(this.convertExecutorToExecutorDTO(executor,convertCriteria));
	}

}
