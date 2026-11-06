package com.fleetmanagementsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.fleetmanagementsystem.domain.Driver;
import com.fleetmanagementsystem.dto.DriverDTO;
import com.fleetmanagementsystem.dto.DriverSearchDTO;
import com.fleetmanagementsystem.dto.DriverPageDTO;
import com.fleetmanagementsystem.dto.DriverConvertCriteriaDTO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DriverService extends GenericService<Driver, Integer> {

	List<Driver> findAll();

	ResultDTO addDriver(DriverDTO driverDTO, RequestDTO requestDTO);

	ResultDTO updateDriver(DriverDTO driverDTO, RequestDTO requestDTO);

    Page<Driver> getAllDrivers(Pageable pageable);

    Page<Driver> getAllDrivers(Specification<Driver> spec, Pageable pageable);

	ResponseEntity<DriverPageDTO> getDrivers(DriverSearchDTO driverSearchDTO);
	
	List<DriverDTO> convertDriversToDriverDTOs(List<Driver> drivers, DriverConvertCriteriaDTO convertCriteria);

	DriverDTO getDriverDTOById(Integer driverId);



	
}
