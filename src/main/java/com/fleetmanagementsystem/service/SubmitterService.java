package com.fleetmanagementsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.fleetmanagementsystem.domain.Submitter;
import com.fleetmanagementsystem.dto.SubmitterDTO;
import com.fleetmanagementsystem.dto.SubmitterSearchDTO;
import com.fleetmanagementsystem.dto.SubmitterPageDTO;
import com.fleetmanagementsystem.dto.SubmitterConvertCriteriaDTO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SubmitterService extends GenericService<Submitter, Integer> {

	List<Submitter> findAll();

	ResultDTO addSubmitter(SubmitterDTO submitterDTO, RequestDTO requestDTO);

	ResultDTO updateSubmitter(SubmitterDTO submitterDTO, RequestDTO requestDTO);

    Page<Submitter> getAllSubmitters(Pageable pageable);

    Page<Submitter> getAllSubmitters(Specification<Submitter> spec, Pageable pageable);

	ResponseEntity<SubmitterPageDTO> getSubmitters(SubmitterSearchDTO submitterSearchDTO);
	
	List<SubmitterDTO> convertSubmittersToSubmitterDTOs(List<Submitter> submitters, SubmitterConvertCriteriaDTO convertCriteria);

	SubmitterDTO getSubmitterDTOById(Integer submitterId);



	
}
