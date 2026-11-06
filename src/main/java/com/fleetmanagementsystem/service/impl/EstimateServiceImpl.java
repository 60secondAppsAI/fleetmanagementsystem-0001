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
import com.fleetmanagementsystem.dao.EstimateDAO;
import com.fleetmanagementsystem.domain.Estimate;
import com.fleetmanagementsystem.dto.EstimateDTO;
import com.fleetmanagementsystem.dto.EstimateSearchDTO;
import com.fleetmanagementsystem.dto.EstimatePageDTO;
import com.fleetmanagementsystem.dto.EstimateConvertCriteriaDTO;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import com.fleetmanagementsystem.service.EstimateService;
import com.fleetmanagementsystem.util.ControllerUtils;


@Service
public class EstimateServiceImpl extends GenericServiceImpl<Estimate, Integer> implements EstimateService {

    private final static Logger logger = LoggerFactory.getLogger(EstimateServiceImpl.class);

	@Autowired
	EstimateDAO estimateDao;

	

	@Override
	public GenericDAO<Estimate, Integer> getDAO() {
		return (GenericDAO<Estimate, Integer>) estimateDao;
	}
	
	public List<Estimate> findAll () {
		List<Estimate> estimates = estimateDao.findAll();
		
		return estimates;	
		
	}

	public ResultDTO addEstimate(EstimateDTO estimateDTO, RequestDTO requestDTO) {

		Estimate estimate = new Estimate();

		estimate.setEstimateId(estimateDTO.getEstimateId());

		estimate.setDateOfDeparture(estimateDTO.getDateOfDeparture());

		estimate.setEstimatedCost(estimateDTO.getEstimatedCost());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		estimate = estimateDao.save(estimate);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Estimate> getAllEstimates(Pageable pageable) {
		return estimateDao.findAll(pageable);
	}

	public Page<Estimate> getAllEstimates(Specification<Estimate> spec, Pageable pageable) {
		return estimateDao.findAll(spec, pageable);
	}

	public ResponseEntity<EstimatePageDTO> getEstimates(EstimateSearchDTO estimateSearchDTO) {
	
			Integer estimateId = estimateSearchDTO.getEstimateId(); 
 			Date dateOfDeparture = estimateSearchDTO.getDateOfDeparture();
  			String sortBy = estimateSearchDTO.getSortBy();
			String sortOrder = estimateSearchDTO.getSortOrder();
			String searchQuery = estimateSearchDTO.getSearchQuery();
			Integer page = estimateSearchDTO.getPage();
			Integer size = estimateSearchDTO.getSize();

	        Specification<Estimate> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, estimateId, "estimateId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, dateOfDeparture, "dateOfDeparture");
			
			

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

		Page<Estimate> estimates = this.getAllEstimates(spec, pageable);
		
		//System.out.println(String.valueOf(estimates.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(estimates.getTotalPages()));
		
		List<Estimate> estimatesList = estimates.getContent();
		
		EstimateConvertCriteriaDTO convertCriteria = new EstimateConvertCriteriaDTO();
		List<EstimateDTO> estimateDTOs = this.convertEstimatesToEstimateDTOs(estimatesList,convertCriteria);
		
		EstimatePageDTO estimatePageDTO = new EstimatePageDTO();
		estimatePageDTO.setEstimates(estimateDTOs);
		estimatePageDTO.setTotalElements(estimates.getTotalElements());
		return ResponseEntity.ok(estimatePageDTO);
	}

	public List<EstimateDTO> convertEstimatesToEstimateDTOs(List<Estimate> estimates, EstimateConvertCriteriaDTO convertCriteria) {
		
		List<EstimateDTO> estimateDTOs = new ArrayList<EstimateDTO>();
		
		for (Estimate estimate : estimates) {
			estimateDTOs.add(convertEstimateToEstimateDTO(estimate,convertCriteria));
		}
		
		return estimateDTOs;

	}
	
	public EstimateDTO convertEstimateToEstimateDTO(Estimate estimate, EstimateConvertCriteriaDTO convertCriteria) {
		
		EstimateDTO estimateDTO = new EstimateDTO();

		estimateDTO.setEstimateId(estimate.getEstimateId());

		estimateDTO.setDateOfDeparture(estimate.getDateOfDeparture());

		estimateDTO.setEstimatedCost(estimate.getEstimatedCost());
		
		return estimateDTO;
	}

	public ResultDTO updateEstimate(EstimateDTO estimateDTO, RequestDTO requestDTO) {
		
		Estimate estimate = estimateDao.getById(estimateDTO.getEstimateId());
		
		estimate.setEstimateId(ControllerUtils.setValue(estimate.getEstimateId(), estimateDTO.getEstimateId()));
		
		estimate.setDateOfDeparture(ControllerUtils.setValue(estimate.getDateOfDeparture(), estimateDTO.getDateOfDeparture()));
		
		estimate.setEstimatedCost(ControllerUtils.setValue(estimate.getEstimatedCost(), estimateDTO.getEstimatedCost()));

        estimate = estimateDao.save(estimate);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public EstimateDTO getEstimateDTOById(Integer estimateId) {
	
		Estimate estimate = estimateDao.getById(estimateId);
		
		EstimateConvertCriteriaDTO convertCriteria = new EstimateConvertCriteriaDTO();
		return(this.convertEstimateToEstimateDTO(estimate,convertCriteria));
	}

}
