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
import com.fleetmanagementsystem.dao.DriverDutyDAO;
import com.fleetmanagementsystem.domain.DriverDuty;
import com.fleetmanagementsystem.dto.DriverDutyDTO;
import com.fleetmanagementsystem.dto.DriverDutySearchDTO;
import com.fleetmanagementsystem.dto.DriverDutyPageDTO;
import com.fleetmanagementsystem.dto.DriverDutyConvertCriteriaDTO;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import com.fleetmanagementsystem.service.DriverDutyService;
import com.fleetmanagementsystem.util.ControllerUtils;


@Service
public class DriverDutyServiceImpl extends GenericServiceImpl<DriverDuty, Integer> implements DriverDutyService {

    private final static Logger logger = LoggerFactory.getLogger(DriverDutyServiceImpl.class);

	@Autowired
	DriverDutyDAO driverDutyDao;

	

	@Override
	public GenericDAO<DriverDuty, Integer> getDAO() {
		return (GenericDAO<DriverDuty, Integer>) driverDutyDao;
	}
	
	public List<DriverDuty> findAll () {
		List<DriverDuty> driverDutys = driverDutyDao.findAll();
		
		return driverDutys;	
		
	}

	public ResultDTO addDriverDuty(DriverDutyDTO driverDutyDTO, RequestDTO requestDTO) {

		DriverDuty driverDuty = new DriverDuty();

		driverDuty.setDriverDutyId(driverDutyDTO.getDriverDutyId());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		driverDuty = driverDutyDao.save(driverDuty);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<DriverDuty> getAllDriverDutys(Pageable pageable) {
		return driverDutyDao.findAll(pageable);
	}

	public Page<DriverDuty> getAllDriverDutys(Specification<DriverDuty> spec, Pageable pageable) {
		return driverDutyDao.findAll(spec, pageable);
	}

	public ResponseEntity<DriverDutyPageDTO> getDriverDutys(DriverDutySearchDTO driverDutySearchDTO) {
	
			Integer driverDutyId = driverDutySearchDTO.getDriverDutyId(); 
 			String sortBy = driverDutySearchDTO.getSortBy();
			String sortOrder = driverDutySearchDTO.getSortOrder();
			String searchQuery = driverDutySearchDTO.getSearchQuery();
			Integer page = driverDutySearchDTO.getPage();
			Integer size = driverDutySearchDTO.getSize();

	        Specification<DriverDuty> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, driverDutyId, "driverDutyId"); 
			

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

		Page<DriverDuty> driverDutys = this.getAllDriverDutys(spec, pageable);
		
		//System.out.println(String.valueOf(driverDutys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(driverDutys.getTotalPages()));
		
		List<DriverDuty> driverDutysList = driverDutys.getContent();
		
		DriverDutyConvertCriteriaDTO convertCriteria = new DriverDutyConvertCriteriaDTO();
		List<DriverDutyDTO> driverDutyDTOs = this.convertDriverDutysToDriverDutyDTOs(driverDutysList,convertCriteria);
		
		DriverDutyPageDTO driverDutyPageDTO = new DriverDutyPageDTO();
		driverDutyPageDTO.setDriverDutys(driverDutyDTOs);
		driverDutyPageDTO.setTotalElements(driverDutys.getTotalElements());
		return ResponseEntity.ok(driverDutyPageDTO);
	}

	public List<DriverDutyDTO> convertDriverDutysToDriverDutyDTOs(List<DriverDuty> driverDutys, DriverDutyConvertCriteriaDTO convertCriteria) {
		
		List<DriverDutyDTO> driverDutyDTOs = new ArrayList<DriverDutyDTO>();
		
		for (DriverDuty driverDuty : driverDutys) {
			driverDutyDTOs.add(convertDriverDutyToDriverDutyDTO(driverDuty,convertCriteria));
		}
		
		return driverDutyDTOs;

	}
	
	public DriverDutyDTO convertDriverDutyToDriverDutyDTO(DriverDuty driverDuty, DriverDutyConvertCriteriaDTO convertCriteria) {
		
		DriverDutyDTO driverDutyDTO = new DriverDutyDTO();

		driverDutyDTO.setDriverDutyId(driverDuty.getDriverDutyId());
		
		return driverDutyDTO;
	}

	public ResultDTO updateDriverDuty(DriverDutyDTO driverDutyDTO, RequestDTO requestDTO) {
		
		DriverDuty driverDuty = driverDutyDao.getById(driverDutyDTO.getDriverDutyId());
		
		driverDuty.setDriverDutyId(ControllerUtils.setValue(driverDuty.getDriverDutyId(), driverDutyDTO.getDriverDutyId()));

        driverDuty = driverDutyDao.save(driverDuty);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DriverDutyDTO getDriverDutyDTOById(Integer driverDutyId) {
	
		DriverDuty driverDuty = driverDutyDao.getById(driverDutyId);
		
		DriverDutyConvertCriteriaDTO convertCriteria = new DriverDutyConvertCriteriaDTO();
		return(this.convertDriverDutyToDriverDutyDTO(driverDuty,convertCriteria));
	}

}
