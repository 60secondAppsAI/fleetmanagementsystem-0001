package com.fleetmanagementsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.fleetmanagementsystem.domain.Estimate;
import com.fleetmanagementsystem.dto.EstimateDTO;
import com.fleetmanagementsystem.dto.EstimateSearchDTO;
import com.fleetmanagementsystem.dto.EstimatePageDTO;
import com.fleetmanagementsystem.dto.EstimateConvertCriteriaDTO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface EstimateService extends GenericService<Estimate, Integer> {

	List<Estimate> findAll();

	ResultDTO addEstimate(EstimateDTO estimateDTO, RequestDTO requestDTO);

	ResultDTO updateEstimate(EstimateDTO estimateDTO, RequestDTO requestDTO);

    Page<Estimate> getAllEstimates(Pageable pageable);

    Page<Estimate> getAllEstimates(Specification<Estimate> spec, Pageable pageable);

	ResponseEntity<EstimatePageDTO> getEstimates(EstimateSearchDTO estimateSearchDTO);
	
	List<EstimateDTO> convertEstimatesToEstimateDTOs(List<Estimate> estimates, EstimateConvertCriteriaDTO convertCriteria);

	EstimateDTO getEstimateDTOById(Integer estimateId);



	
}
