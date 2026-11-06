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
import com.fleetmanagementsystem.dao.DriverDAO;
import com.fleetmanagementsystem.domain.Driver;
import com.fleetmanagementsystem.dto.DriverDTO;
import com.fleetmanagementsystem.dto.DriverSearchDTO;
import com.fleetmanagementsystem.dto.DriverPageDTO;
import com.fleetmanagementsystem.dto.DriverConvertCriteriaDTO;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import com.fleetmanagementsystem.service.DriverService;
import com.fleetmanagementsystem.util.ControllerUtils;


@Service
public class DriverServiceImpl extends GenericServiceImpl<Driver, Integer> implements DriverService {

    private final static Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);

	@Autowired
	DriverDAO driverDao;

	

	@Override
	public GenericDAO<Driver, Integer> getDAO() {
		return (GenericDAO<Driver, Integer>) driverDao;
	}
	
	public List<Driver> findAll () {
		List<Driver> drivers = driverDao.findAll();
		
		return drivers;	
		
	}

	public ResultDTO addDriver(DriverDTO driverDTO, RequestDTO requestDTO) {

		Driver driver = new Driver();

		driver.setDriverId(driverDTO.getDriverId());

		driver.setFullName(driverDTO.getFullName());

		driver.setLicenseCode(driverDTO.getLicenseCode());

		driver.setContactInfo(driverDTO.getContactInfo());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		driver = driverDao.save(driver);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Driver> getAllDrivers(Pageable pageable) {
		return driverDao.findAll(pageable);
	}

	public Page<Driver> getAllDrivers(Specification<Driver> spec, Pageable pageable) {
		return driverDao.findAll(spec, pageable);
	}

	public ResponseEntity<DriverPageDTO> getDrivers(DriverSearchDTO driverSearchDTO) {
	
			Integer driverId = driverSearchDTO.getDriverId(); 
 			String fullName = driverSearchDTO.getFullName(); 
 			String licenseCode = driverSearchDTO.getLicenseCode(); 
 			String contactInfo = driverSearchDTO.getContactInfo(); 
 			String sortBy = driverSearchDTO.getSortBy();
			String sortOrder = driverSearchDTO.getSortOrder();
			String searchQuery = driverSearchDTO.getSearchQuery();
			Integer page = driverSearchDTO.getPage();
			Integer size = driverSearchDTO.getSize();

	        Specification<Driver> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, driverId, "driverId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, fullName, "fullName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, licenseCode, "licenseCode"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contactInfo, "contactInfo"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("fullName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("licenseCode")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contactInfo")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Driver> drivers = this.getAllDrivers(spec, pageable);
		
		//System.out.println(String.valueOf(drivers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(drivers.getTotalPages()));
		
		List<Driver> driversList = drivers.getContent();
		
		DriverConvertCriteriaDTO convertCriteria = new DriverConvertCriteriaDTO();
		List<DriverDTO> driverDTOs = this.convertDriversToDriverDTOs(driversList,convertCriteria);
		
		DriverPageDTO driverPageDTO = new DriverPageDTO();
		driverPageDTO.setDrivers(driverDTOs);
		driverPageDTO.setTotalElements(drivers.getTotalElements());
		return ResponseEntity.ok(driverPageDTO);
	}

	public List<DriverDTO> convertDriversToDriverDTOs(List<Driver> drivers, DriverConvertCriteriaDTO convertCriteria) {
		
		List<DriverDTO> driverDTOs = new ArrayList<DriverDTO>();
		
		for (Driver driver : drivers) {
			driverDTOs.add(convertDriverToDriverDTO(driver,convertCriteria));
		}
		
		return driverDTOs;

	}
	
	public DriverDTO convertDriverToDriverDTO(Driver driver, DriverConvertCriteriaDTO convertCriteria) {
		
		DriverDTO driverDTO = new DriverDTO();

		driverDTO.setDriverId(driver.getDriverId());

		driverDTO.setFullName(driver.getFullName());

		driverDTO.setLicenseCode(driver.getLicenseCode());

		driverDTO.setContactInfo(driver.getContactInfo());
		
		return driverDTO;
	}

	public ResultDTO updateDriver(DriverDTO driverDTO, RequestDTO requestDTO) {
		
		Driver driver = driverDao.getById(driverDTO.getDriverId());
		
		driver.setDriverId(ControllerUtils.setValue(driver.getDriverId(), driverDTO.getDriverId()));
		
		driver.setFullName(ControllerUtils.setValue(driver.getFullName(), driverDTO.getFullName()));
		
		driver.setLicenseCode(ControllerUtils.setValue(driver.getLicenseCode(), driverDTO.getLicenseCode()));
		
		driver.setContactInfo(ControllerUtils.setValue(driver.getContactInfo(), driverDTO.getContactInfo()));

        driver = driverDao.save(driver);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DriverDTO getDriverDTOById(Integer driverId) {
	
		Driver driver = driverDao.getById(driverId);
		
		DriverConvertCriteriaDTO convertCriteria = new DriverConvertCriteriaDTO();
		return(this.convertDriverToDriverDTO(driver,convertCriteria));
	}

}
