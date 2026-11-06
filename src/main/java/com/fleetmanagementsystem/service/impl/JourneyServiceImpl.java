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
import com.fleetmanagementsystem.dao.JourneyDAO;
import com.fleetmanagementsystem.domain.Journey;
import com.fleetmanagementsystem.dto.JourneyDTO;
import com.fleetmanagementsystem.dto.JourneySearchDTO;
import com.fleetmanagementsystem.dto.JourneyPageDTO;
import com.fleetmanagementsystem.dto.JourneyConvertCriteriaDTO;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import com.fleetmanagementsystem.service.JourneyService;
import com.fleetmanagementsystem.util.ControllerUtils;


@Service
public class JourneyServiceImpl extends GenericServiceImpl<Journey, Integer> implements JourneyService {

    private final static Logger logger = LoggerFactory.getLogger(JourneyServiceImpl.class);

	@Autowired
	JourneyDAO journeyDao;

	

	@Override
	public GenericDAO<Journey, Integer> getDAO() {
		return (GenericDAO<Journey, Integer>) journeyDao;
	}
	
	public List<Journey> findAll () {
		List<Journey> journeys = journeyDao.findAll();
		
		return journeys;	
		
	}

	public ResultDTO addJourney(JourneyDTO journeyDTO, RequestDTO requestDTO) {

		Journey journey = new Journey();

		journey.setJourneyId(journeyDTO.getJourneyId());

		journey.setStartPoint(journeyDTO.getStartPoint());

		journey.setEndPoint(journeyDTO.getEndPoint());

		journey.setDistanceTraveled(journeyDTO.getDistanceTraveled());

		journey.setFare(journeyDTO.getFare());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		journey = journeyDao.save(journey);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Journey> getAllJourneys(Pageable pageable) {
		return journeyDao.findAll(pageable);
	}

	public Page<Journey> getAllJourneys(Specification<Journey> spec, Pageable pageable) {
		return journeyDao.findAll(spec, pageable);
	}

	public ResponseEntity<JourneyPageDTO> getJourneys(JourneySearchDTO journeySearchDTO) {
	
			Integer journeyId = journeySearchDTO.getJourneyId(); 
 			String startPoint = journeySearchDTO.getStartPoint(); 
 			String endPoint = journeySearchDTO.getEndPoint(); 
   			String sortBy = journeySearchDTO.getSortBy();
			String sortOrder = journeySearchDTO.getSortOrder();
			String searchQuery = journeySearchDTO.getSearchQuery();
			Integer page = journeySearchDTO.getPage();
			Integer size = journeySearchDTO.getSize();

	        Specification<Journey> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, journeyId, "journeyId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, startPoint, "startPoint"); 
			
			spec = ControllerUtils.andIfNecessary(spec, endPoint, "endPoint"); 
			
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("startPoint")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("endPoint")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Journey> journeys = this.getAllJourneys(spec, pageable);
		
		//System.out.println(String.valueOf(journeys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(journeys.getTotalPages()));
		
		List<Journey> journeysList = journeys.getContent();
		
		JourneyConvertCriteriaDTO convertCriteria = new JourneyConvertCriteriaDTO();
		List<JourneyDTO> journeyDTOs = this.convertJourneysToJourneyDTOs(journeysList,convertCriteria);
		
		JourneyPageDTO journeyPageDTO = new JourneyPageDTO();
		journeyPageDTO.setJourneys(journeyDTOs);
		journeyPageDTO.setTotalElements(journeys.getTotalElements());
		return ResponseEntity.ok(journeyPageDTO);
	}

	public List<JourneyDTO> convertJourneysToJourneyDTOs(List<Journey> journeys, JourneyConvertCriteriaDTO convertCriteria) {
		
		List<JourneyDTO> journeyDTOs = new ArrayList<JourneyDTO>();
		
		for (Journey journey : journeys) {
			journeyDTOs.add(convertJourneyToJourneyDTO(journey,convertCriteria));
		}
		
		return journeyDTOs;

	}
	
	public JourneyDTO convertJourneyToJourneyDTO(Journey journey, JourneyConvertCriteriaDTO convertCriteria) {
		
		JourneyDTO journeyDTO = new JourneyDTO();

		journeyDTO.setJourneyId(journey.getJourneyId());

		journeyDTO.setStartPoint(journey.getStartPoint());

		journeyDTO.setEndPoint(journey.getEndPoint());

		journeyDTO.setDistanceTraveled(journey.getDistanceTraveled());

		journeyDTO.setFare(journey.getFare());
		
		return journeyDTO;
	}

	public ResultDTO updateJourney(JourneyDTO journeyDTO, RequestDTO requestDTO) {
		
		Journey journey = journeyDao.getById(journeyDTO.getJourneyId());
		
		journey.setJourneyId(ControllerUtils.setValue(journey.getJourneyId(), journeyDTO.getJourneyId()));
		
		journey.setStartPoint(ControllerUtils.setValue(journey.getStartPoint(), journeyDTO.getStartPoint()));
		
		journey.setEndPoint(ControllerUtils.setValue(journey.getEndPoint(), journeyDTO.getEndPoint()));
		
		journey.setDistanceTraveled(ControllerUtils.setValue(journey.getDistanceTraveled(), journeyDTO.getDistanceTraveled()));
		
		journey.setFare(ControllerUtils.setValue(journey.getFare(), journeyDTO.getFare()));

        journey = journeyDao.save(journey);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public JourneyDTO getJourneyDTOById(Integer journeyId) {
	
		Journey journey = journeyDao.getById(journeyId);
		
		JourneyConvertCriteriaDTO convertCriteria = new JourneyConvertCriteriaDTO();
		return(this.convertJourneyToJourneyDTO(journey,convertCriteria));
	}

}
