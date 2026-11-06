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
import com.fleetmanagementsystem.dao.VehicleDutyDAO;
import com.fleetmanagementsystem.domain.VehicleDuty;
import com.fleetmanagementsystem.dto.VehicleDutyDTO;
import com.fleetmanagementsystem.dto.VehicleDutySearchDTO;
import com.fleetmanagementsystem.dto.VehicleDutyPageDTO;
import com.fleetmanagementsystem.dto.VehicleDutyConvertCriteriaDTO;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import com.fleetmanagementsystem.service.VehicleDutyService;
import com.fleetmanagementsystem.util.ControllerUtils;


@Service
public class VehicleDutyServiceImpl extends GenericServiceImpl<VehicleDuty, Integer> implements VehicleDutyService {

    private final static Logger logger = LoggerFactory.getLogger(VehicleDutyServiceImpl.class);

	@Autowired
	VehicleDutyDAO vehicleDutyDao;

	

	@Override
	public GenericDAO<VehicleDuty, Integer> getDAO() {
		return (GenericDAO<VehicleDuty, Integer>) vehicleDutyDao;
	}
	
	public List<VehicleDuty> findAll () {
		List<VehicleDuty> vehicleDutys = vehicleDutyDao.findAll();
		
		return vehicleDutys;	
		
	}

	public ResultDTO addVehicleDuty(VehicleDutyDTO vehicleDutyDTO, RequestDTO requestDTO) {

		VehicleDuty vehicleDuty = new VehicleDuty();

		vehicleDuty.setVehicleDutyId(vehicleDutyDTO.getVehicleDutyId());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		vehicleDuty = vehicleDutyDao.save(vehicleDuty);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<VehicleDuty> getAllVehicleDutys(Pageable pageable) {
		return vehicleDutyDao.findAll(pageable);
	}

	public Page<VehicleDuty> getAllVehicleDutys(Specification<VehicleDuty> spec, Pageable pageable) {
		return vehicleDutyDao.findAll(spec, pageable);
	}

	public ResponseEntity<VehicleDutyPageDTO> getVehicleDutys(VehicleDutySearchDTO vehicleDutySearchDTO) {
	
			Integer vehicleDutyId = vehicleDutySearchDTO.getVehicleDutyId(); 
 			String sortBy = vehicleDutySearchDTO.getSortBy();
			String sortOrder = vehicleDutySearchDTO.getSortOrder();
			String searchQuery = vehicleDutySearchDTO.getSearchQuery();
			Integer page = vehicleDutySearchDTO.getPage();
			Integer size = vehicleDutySearchDTO.getSize();

	        Specification<VehicleDuty> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, vehicleDutyId, "vehicleDutyId"); 
			

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

		Page<VehicleDuty> vehicleDutys = this.getAllVehicleDutys(spec, pageable);
		
		//System.out.println(String.valueOf(vehicleDutys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(vehicleDutys.getTotalPages()));
		
		List<VehicleDuty> vehicleDutysList = vehicleDutys.getContent();
		
		VehicleDutyConvertCriteriaDTO convertCriteria = new VehicleDutyConvertCriteriaDTO();
		List<VehicleDutyDTO> vehicleDutyDTOs = this.convertVehicleDutysToVehicleDutyDTOs(vehicleDutysList,convertCriteria);
		
		VehicleDutyPageDTO vehicleDutyPageDTO = new VehicleDutyPageDTO();
		vehicleDutyPageDTO.setVehicleDutys(vehicleDutyDTOs);
		vehicleDutyPageDTO.setTotalElements(vehicleDutys.getTotalElements());
		return ResponseEntity.ok(vehicleDutyPageDTO);
	}

	public List<VehicleDutyDTO> convertVehicleDutysToVehicleDutyDTOs(List<VehicleDuty> vehicleDutys, VehicleDutyConvertCriteriaDTO convertCriteria) {
		
		List<VehicleDutyDTO> vehicleDutyDTOs = new ArrayList<VehicleDutyDTO>();
		
		for (VehicleDuty vehicleDuty : vehicleDutys) {
			vehicleDutyDTOs.add(convertVehicleDutyToVehicleDutyDTO(vehicleDuty,convertCriteria));
		}
		
		return vehicleDutyDTOs;

	}
	
	public VehicleDutyDTO convertVehicleDutyToVehicleDutyDTO(VehicleDuty vehicleDuty, VehicleDutyConvertCriteriaDTO convertCriteria) {
		
		VehicleDutyDTO vehicleDutyDTO = new VehicleDutyDTO();

		vehicleDutyDTO.setVehicleDutyId(vehicleDuty.getVehicleDutyId());
		
		return vehicleDutyDTO;
	}

	public ResultDTO updateVehicleDuty(VehicleDutyDTO vehicleDutyDTO, RequestDTO requestDTO) {
		
		VehicleDuty vehicleDuty = vehicleDutyDao.getById(vehicleDutyDTO.getVehicleDutyId());
		
		vehicleDuty.setVehicleDutyId(ControllerUtils.setValue(vehicleDuty.getVehicleDutyId(), vehicleDutyDTO.getVehicleDutyId()));

        vehicleDuty = vehicleDutyDao.save(vehicleDuty);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public VehicleDutyDTO getVehicleDutyDTOById(Integer vehicleDutyId) {
	
		VehicleDuty vehicleDuty = vehicleDutyDao.getById(vehicleDutyId);
		
		VehicleDutyConvertCriteriaDTO convertCriteria = new VehicleDutyConvertCriteriaDTO();
		return(this.convertVehicleDutyToVehicleDutyDTO(vehicleDuty,convertCriteria));
	}

}
