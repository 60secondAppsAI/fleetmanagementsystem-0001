package com.fleetmanagementsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.fleetmanagementsystem.domain.VehicleDuty;
import com.fleetmanagementsystem.dto.VehicleDutyDTO;
import com.fleetmanagementsystem.dto.VehicleDutySearchDTO;
import com.fleetmanagementsystem.dto.VehicleDutyPageDTO;
import com.fleetmanagementsystem.dto.VehicleDutyConvertCriteriaDTO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface VehicleDutyService extends GenericService<VehicleDuty, Integer> {

	List<VehicleDuty> findAll();

	ResultDTO addVehicleDuty(VehicleDutyDTO vehicleDutyDTO, RequestDTO requestDTO);

	ResultDTO updateVehicleDuty(VehicleDutyDTO vehicleDutyDTO, RequestDTO requestDTO);

    Page<VehicleDuty> getAllVehicleDutys(Pageable pageable);

    Page<VehicleDuty> getAllVehicleDutys(Specification<VehicleDuty> spec, Pageable pageable);

	ResponseEntity<VehicleDutyPageDTO> getVehicleDutys(VehicleDutySearchDTO vehicleDutySearchDTO);
	
	List<VehicleDutyDTO> convertVehicleDutysToVehicleDutyDTOs(List<VehicleDuty> vehicleDutys, VehicleDutyConvertCriteriaDTO convertCriteria);

	VehicleDutyDTO getVehicleDutyDTOById(Integer vehicleDutyId);



	
}
