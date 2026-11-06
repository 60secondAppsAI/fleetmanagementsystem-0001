package com.fleetmanagementsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.fleetmanagementsystem.domain.DriverDuty;
import com.fleetmanagementsystem.dto.DriverDutyDTO;
import com.fleetmanagementsystem.dto.DriverDutySearchDTO;
import com.fleetmanagementsystem.dto.DriverDutyPageDTO;
import com.fleetmanagementsystem.dto.DriverDutyConvertCriteriaDTO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DriverDutyService extends GenericService<DriverDuty, Integer> {

	List<DriverDuty> findAll();

	ResultDTO addDriverDuty(DriverDutyDTO driverDutyDTO, RequestDTO requestDTO);

	ResultDTO updateDriverDuty(DriverDutyDTO driverDutyDTO, RequestDTO requestDTO);

    Page<DriverDuty> getAllDriverDutys(Pageable pageable);

    Page<DriverDuty> getAllDriverDutys(Specification<DriverDuty> spec, Pageable pageable);

	ResponseEntity<DriverDutyPageDTO> getDriverDutys(DriverDutySearchDTO driverDutySearchDTO);
	
	List<DriverDutyDTO> convertDriverDutysToDriverDutyDTOs(List<DriverDuty> driverDutys, DriverDutyConvertCriteriaDTO convertCriteria);

	DriverDutyDTO getDriverDutyDTOById(Integer driverDutyId);



	
}
