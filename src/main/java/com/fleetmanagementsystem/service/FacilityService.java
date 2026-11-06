package com.fleetmanagementsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.fleetmanagementsystem.domain.Facility;
import com.fleetmanagementsystem.dto.FacilityDTO;
import com.fleetmanagementsystem.dto.FacilitySearchDTO;
import com.fleetmanagementsystem.dto.FacilityPageDTO;
import com.fleetmanagementsystem.dto.FacilityConvertCriteriaDTO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FacilityService extends GenericService<Facility, Integer> {

	List<Facility> findAll();

	ResultDTO addFacility(FacilityDTO facilityDTO, RequestDTO requestDTO);

	ResultDTO updateFacility(FacilityDTO facilityDTO, RequestDTO requestDTO);

    Page<Facility> getAllFacilitys(Pageable pageable);

    Page<Facility> getAllFacilitys(Specification<Facility> spec, Pageable pageable);

	ResponseEntity<FacilityPageDTO> getFacilitys(FacilitySearchDTO facilitySearchDTO);
	
	List<FacilityDTO> convertFacilitysToFacilityDTOs(List<Facility> facilitys, FacilityConvertCriteriaDTO convertCriteria);

	FacilityDTO getFacilityDTOById(Integer facilityId);



	
}
