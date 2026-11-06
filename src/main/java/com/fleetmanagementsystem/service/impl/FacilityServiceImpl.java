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
import com.fleetmanagementsystem.dao.FacilityDAO;
import com.fleetmanagementsystem.domain.Facility;
import com.fleetmanagementsystem.dto.FacilityDTO;
import com.fleetmanagementsystem.dto.FacilitySearchDTO;
import com.fleetmanagementsystem.dto.FacilityPageDTO;
import com.fleetmanagementsystem.dto.FacilityConvertCriteriaDTO;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import com.fleetmanagementsystem.service.FacilityService;
import com.fleetmanagementsystem.util.ControllerUtils;


@Service
public class FacilityServiceImpl extends GenericServiceImpl<Facility, Integer> implements FacilityService {

    private final static Logger logger = LoggerFactory.getLogger(FacilityServiceImpl.class);

	@Autowired
	FacilityDAO facilityDao;

	

	@Override
	public GenericDAO<Facility, Integer> getDAO() {
		return (GenericDAO<Facility, Integer>) facilityDao;
	}
	
	public List<Facility> findAll () {
		List<Facility> facilitys = facilityDao.findAll();
		
		return facilitys;	
		
	}

	public ResultDTO addFacility(FacilityDTO facilityDTO, RequestDTO requestDTO) {

		Facility facility = new Facility();

		facility.setFacilityId(facilityDTO.getFacilityId());

		facility.setFacilityName(facilityDTO.getFacilityName());

		facility.setFacilityDescription(facilityDTO.getFacilityDescription());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		facility = facilityDao.save(facility);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Facility> getAllFacilitys(Pageable pageable) {
		return facilityDao.findAll(pageable);
	}

	public Page<Facility> getAllFacilitys(Specification<Facility> spec, Pageable pageable) {
		return facilityDao.findAll(spec, pageable);
	}

	public ResponseEntity<FacilityPageDTO> getFacilitys(FacilitySearchDTO facilitySearchDTO) {
	
			Integer facilityId = facilitySearchDTO.getFacilityId(); 
 			String facilityName = facilitySearchDTO.getFacilityName(); 
 			String facilityDescription = facilitySearchDTO.getFacilityDescription(); 
 			String sortBy = facilitySearchDTO.getSortBy();
			String sortOrder = facilitySearchDTO.getSortOrder();
			String searchQuery = facilitySearchDTO.getSearchQuery();
			Integer page = facilitySearchDTO.getPage();
			Integer size = facilitySearchDTO.getSize();

	        Specification<Facility> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, facilityId, "facilityId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, facilityName, "facilityName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, facilityDescription, "facilityDescription"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("facilityName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("facilityDescription")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Facility> facilitys = this.getAllFacilitys(spec, pageable);
		
		//System.out.println(String.valueOf(facilitys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(facilitys.getTotalPages()));
		
		List<Facility> facilitysList = facilitys.getContent();
		
		FacilityConvertCriteriaDTO convertCriteria = new FacilityConvertCriteriaDTO();
		List<FacilityDTO> facilityDTOs = this.convertFacilitysToFacilityDTOs(facilitysList,convertCriteria);
		
		FacilityPageDTO facilityPageDTO = new FacilityPageDTO();
		facilityPageDTO.setFacilitys(facilityDTOs);
		facilityPageDTO.setTotalElements(facilitys.getTotalElements());
		return ResponseEntity.ok(facilityPageDTO);
	}

	public List<FacilityDTO> convertFacilitysToFacilityDTOs(List<Facility> facilitys, FacilityConvertCriteriaDTO convertCriteria) {
		
		List<FacilityDTO> facilityDTOs = new ArrayList<FacilityDTO>();
		
		for (Facility facility : facilitys) {
			facilityDTOs.add(convertFacilityToFacilityDTO(facility,convertCriteria));
		}
		
		return facilityDTOs;

	}
	
	public FacilityDTO convertFacilityToFacilityDTO(Facility facility, FacilityConvertCriteriaDTO convertCriteria) {
		
		FacilityDTO facilityDTO = new FacilityDTO();

		facilityDTO.setFacilityId(facility.getFacilityId());

		facilityDTO.setFacilityName(facility.getFacilityName());

		facilityDTO.setFacilityDescription(facility.getFacilityDescription());
		
		return facilityDTO;
	}

	public ResultDTO updateFacility(FacilityDTO facilityDTO, RequestDTO requestDTO) {
		
		Facility facility = facilityDao.getById(facilityDTO.getFacilityId());
		
		facility.setFacilityId(ControllerUtils.setValue(facility.getFacilityId(), facilityDTO.getFacilityId()));
		
		facility.setFacilityName(ControllerUtils.setValue(facility.getFacilityName(), facilityDTO.getFacilityName()));
		
		facility.setFacilityDescription(ControllerUtils.setValue(facility.getFacilityDescription(), facilityDTO.getFacilityDescription()));

        facility = facilityDao.save(facility);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public FacilityDTO getFacilityDTOById(Integer facilityId) {
	
		Facility facility = facilityDao.getById(facilityId);
		
		FacilityConvertCriteriaDTO convertCriteria = new FacilityConvertCriteriaDTO();
		return(this.convertFacilityToFacilityDTO(facility,convertCriteria));
	}

}
