package com.fleetmanagementsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.fleetmanagementsystem.domain.Journey;
import com.fleetmanagementsystem.dto.JourneyDTO;
import com.fleetmanagementsystem.dto.JourneySearchDTO;
import com.fleetmanagementsystem.dto.JourneyPageDTO;
import com.fleetmanagementsystem.dto.JourneyConvertCriteriaDTO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface JourneyService extends GenericService<Journey, Integer> {

	List<Journey> findAll();

	ResultDTO addJourney(JourneyDTO journeyDTO, RequestDTO requestDTO);

	ResultDTO updateJourney(JourneyDTO journeyDTO, RequestDTO requestDTO);

    Page<Journey> getAllJourneys(Pageable pageable);

    Page<Journey> getAllJourneys(Specification<Journey> spec, Pageable pageable);

	ResponseEntity<JourneyPageDTO> getJourneys(JourneySearchDTO journeySearchDTO);
	
	List<JourneyDTO> convertJourneysToJourneyDTOs(List<Journey> journeys, JourneyConvertCriteriaDTO convertCriteria);

	JourneyDTO getJourneyDTOById(Integer journeyId);



	
}
