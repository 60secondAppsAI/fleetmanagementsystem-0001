package com.fleetmanagementsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.fleetmanagementsystem.domain.Vehicle;
import com.fleetmanagementsystem.dto.VehicleDTO;
import com.fleetmanagementsystem.dto.VehicleSearchDTO;
import com.fleetmanagementsystem.dto.VehiclePageDTO;
import com.fleetmanagementsystem.dto.VehicleConvertCriteriaDTO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface VehicleService extends GenericService<Vehicle, Integer> {

	List<Vehicle> findAll();

	ResultDTO addVehicle(VehicleDTO vehicleDTO, RequestDTO requestDTO);

	ResultDTO updateVehicle(VehicleDTO vehicleDTO, RequestDTO requestDTO);

    Page<Vehicle> getAllVehicles(Pageable pageable);

    Page<Vehicle> getAllVehicles(Specification<Vehicle> spec, Pageable pageable);

	ResponseEntity<VehiclePageDTO> getVehicles(VehicleSearchDTO vehicleSearchDTO);
	
	List<VehicleDTO> convertVehiclesToVehicleDTOs(List<Vehicle> vehicles, VehicleConvertCriteriaDTO convertCriteria);

	VehicleDTO getVehicleDTOById(Integer vehicleId);



	
}
