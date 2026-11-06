package com.fleetmanagementsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.fleetmanagementsystem.domain.Duty;
import com.fleetmanagementsystem.dto.DutyDTO;
import com.fleetmanagementsystem.dto.DutySearchDTO;
import com.fleetmanagementsystem.dto.DutyPageDTO;
import com.fleetmanagementsystem.dto.DutyConvertCriteriaDTO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DutyService extends GenericService<Duty, Integer> {

	List<Duty> findAll();

	ResultDTO addDuty(DutyDTO dutyDTO, RequestDTO requestDTO);

	ResultDTO updateDuty(DutyDTO dutyDTO, RequestDTO requestDTO);

    Page<Duty> getAllDutys(Pageable pageable);

    Page<Duty> getAllDutys(Specification<Duty> spec, Pageable pageable);

	ResponseEntity<DutyPageDTO> getDutys(DutySearchDTO dutySearchDTO);
	
	List<DutyDTO> convertDutysToDutyDTOs(List<Duty> dutys, DutyConvertCriteriaDTO convertCriteria);

	DutyDTO getDutyDTOById(Integer dutyId);



	
}
