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
import com.fleetmanagementsystem.dao.DutyDAO;
import com.fleetmanagementsystem.domain.Duty;
import com.fleetmanagementsystem.dto.DutyDTO;
import com.fleetmanagementsystem.dto.DutySearchDTO;
import com.fleetmanagementsystem.dto.DutyPageDTO;
import com.fleetmanagementsystem.dto.DutyConvertCriteriaDTO;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import com.fleetmanagementsystem.service.DutyService;
import com.fleetmanagementsystem.util.ControllerUtils;


@Service
public class DutyServiceImpl extends GenericServiceImpl<Duty, Integer> implements DutyService {

    private final static Logger logger = LoggerFactory.getLogger(DutyServiceImpl.class);

	@Autowired
	DutyDAO dutyDao;

	

	@Override
	public GenericDAO<Duty, Integer> getDAO() {
		return (GenericDAO<Duty, Integer>) dutyDao;
	}
	
	public List<Duty> findAll () {
		List<Duty> dutys = dutyDao.findAll();
		
		return dutys;	
		
	}

	public ResultDTO addDuty(DutyDTO dutyDTO, RequestDTO requestDTO) {

		Duty duty = new Duty();

		duty.setDutyId(dutyDTO.getDutyId());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		duty = dutyDao.save(duty);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Duty> getAllDutys(Pageable pageable) {
		return dutyDao.findAll(pageable);
	}

	public Page<Duty> getAllDutys(Specification<Duty> spec, Pageable pageable) {
		return dutyDao.findAll(spec, pageable);
	}

	public ResponseEntity<DutyPageDTO> getDutys(DutySearchDTO dutySearchDTO) {
	
			Integer dutyId = dutySearchDTO.getDutyId(); 
 			String sortBy = dutySearchDTO.getSortBy();
			String sortOrder = dutySearchDTO.getSortOrder();
			String searchQuery = dutySearchDTO.getSearchQuery();
			Integer page = dutySearchDTO.getPage();
			Integer size = dutySearchDTO.getSize();

	        Specification<Duty> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, dutyId, "dutyId"); 
			

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

		Page<Duty> dutys = this.getAllDutys(spec, pageable);
		
		//System.out.println(String.valueOf(dutys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(dutys.getTotalPages()));
		
		List<Duty> dutysList = dutys.getContent();
		
		DutyConvertCriteriaDTO convertCriteria = new DutyConvertCriteriaDTO();
		List<DutyDTO> dutyDTOs = this.convertDutysToDutyDTOs(dutysList,convertCriteria);
		
		DutyPageDTO dutyPageDTO = new DutyPageDTO();
		dutyPageDTO.setDutys(dutyDTOs);
		dutyPageDTO.setTotalElements(dutys.getTotalElements());
		return ResponseEntity.ok(dutyPageDTO);
	}

	public List<DutyDTO> convertDutysToDutyDTOs(List<Duty> dutys, DutyConvertCriteriaDTO convertCriteria) {
		
		List<DutyDTO> dutyDTOs = new ArrayList<DutyDTO>();
		
		for (Duty duty : dutys) {
			dutyDTOs.add(convertDutyToDutyDTO(duty,convertCriteria));
		}
		
		return dutyDTOs;

	}
	
	public DutyDTO convertDutyToDutyDTO(Duty duty, DutyConvertCriteriaDTO convertCriteria) {
		
		DutyDTO dutyDTO = new DutyDTO();

		dutyDTO.setDutyId(duty.getDutyId());
		
		return dutyDTO;
	}

	public ResultDTO updateDuty(DutyDTO dutyDTO, RequestDTO requestDTO) {
		
		Duty duty = dutyDao.getById(dutyDTO.getDutyId());
		
		duty.setDutyId(ControllerUtils.setValue(duty.getDutyId(), dutyDTO.getDutyId()));

        duty = dutyDao.save(duty);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DutyDTO getDutyDTOById(Integer dutyId) {
	
		Duty duty = dutyDao.getById(dutyId);
		
		DutyConvertCriteriaDTO convertCriteria = new DutyConvertCriteriaDTO();
		return(this.convertDutyToDutyDTO(duty,convertCriteria));
	}

}
