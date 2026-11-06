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
import com.fleetmanagementsystem.dao.SubmitterDAO;
import com.fleetmanagementsystem.domain.Submitter;
import com.fleetmanagementsystem.dto.SubmitterDTO;
import com.fleetmanagementsystem.dto.SubmitterSearchDTO;
import com.fleetmanagementsystem.dto.SubmitterPageDTO;
import com.fleetmanagementsystem.dto.SubmitterConvertCriteriaDTO;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import com.fleetmanagementsystem.service.SubmitterService;
import com.fleetmanagementsystem.util.ControllerUtils;


@Service
public class SubmitterServiceImpl extends GenericServiceImpl<Submitter, Integer> implements SubmitterService {

    private final static Logger logger = LoggerFactory.getLogger(SubmitterServiceImpl.class);

	@Autowired
	SubmitterDAO submitterDao;

	

	@Override
	public GenericDAO<Submitter, Integer> getDAO() {
		return (GenericDAO<Submitter, Integer>) submitterDao;
	}
	
	public List<Submitter> findAll () {
		List<Submitter> submitters = submitterDao.findAll();
		
		return submitters;	
		
	}

	public ResultDTO addSubmitter(SubmitterDTO submitterDTO, RequestDTO requestDTO) {

		Submitter submitter = new Submitter();

		submitter.setSubmitterId(submitterDTO.getSubmitterId());

		submitter.setTaskId(submitterDTO.getTaskId());

		submitter.setUserId(submitterDTO.getUserId());

		submitter.setSubmissionDate(submitterDTO.getSubmissionDate());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		submitter = submitterDao.save(submitter);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Submitter> getAllSubmitters(Pageable pageable) {
		return submitterDao.findAll(pageable);
	}

	public Page<Submitter> getAllSubmitters(Specification<Submitter> spec, Pageable pageable) {
		return submitterDao.findAll(spec, pageable);
	}

	public ResponseEntity<SubmitterPageDTO> getSubmitters(SubmitterSearchDTO submitterSearchDTO) {
	
			Integer submitterId = submitterSearchDTO.getSubmitterId(); 
   			Date submissionDate = submitterSearchDTO.getSubmissionDate();
 			String sortBy = submitterSearchDTO.getSortBy();
			String sortOrder = submitterSearchDTO.getSortOrder();
			String searchQuery = submitterSearchDTO.getSearchQuery();
			Integer page = submitterSearchDTO.getPage();
			Integer size = submitterSearchDTO.getSize();

	        Specification<Submitter> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, submitterId, "submitterId"); 
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, submissionDate, "submissionDate");
			

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

		Page<Submitter> submitters = this.getAllSubmitters(spec, pageable);
		
		//System.out.println(String.valueOf(submitters.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(submitters.getTotalPages()));
		
		List<Submitter> submittersList = submitters.getContent();
		
		SubmitterConvertCriteriaDTO convertCriteria = new SubmitterConvertCriteriaDTO();
		List<SubmitterDTO> submitterDTOs = this.convertSubmittersToSubmitterDTOs(submittersList,convertCriteria);
		
		SubmitterPageDTO submitterPageDTO = new SubmitterPageDTO();
		submitterPageDTO.setSubmitters(submitterDTOs);
		submitterPageDTO.setTotalElements(submitters.getTotalElements());
		return ResponseEntity.ok(submitterPageDTO);
	}

	public List<SubmitterDTO> convertSubmittersToSubmitterDTOs(List<Submitter> submitters, SubmitterConvertCriteriaDTO convertCriteria) {
		
		List<SubmitterDTO> submitterDTOs = new ArrayList<SubmitterDTO>();
		
		for (Submitter submitter : submitters) {
			submitterDTOs.add(convertSubmitterToSubmitterDTO(submitter,convertCriteria));
		}
		
		return submitterDTOs;

	}
	
	public SubmitterDTO convertSubmitterToSubmitterDTO(Submitter submitter, SubmitterConvertCriteriaDTO convertCriteria) {
		
		SubmitterDTO submitterDTO = new SubmitterDTO();

		submitterDTO.setSubmitterId(submitter.getSubmitterId());

		submitterDTO.setTaskId(submitter.getTaskId());

		submitterDTO.setUserId(submitter.getUserId());

		submitterDTO.setSubmissionDate(submitter.getSubmissionDate());
		
		return submitterDTO;
	}

	public ResultDTO updateSubmitter(SubmitterDTO submitterDTO, RequestDTO requestDTO) {
		
		Submitter submitter = submitterDao.getById(submitterDTO.getSubmitterId());
		
		submitter.setSubmitterId(ControllerUtils.setValue(submitter.getSubmitterId(), submitterDTO.getSubmitterId()));
		
		submitter.setTaskId(ControllerUtils.setValue(submitter.getTaskId(), submitterDTO.getTaskId()));
		
		submitter.setUserId(ControllerUtils.setValue(submitter.getUserId(), submitterDTO.getUserId()));
		
		submitter.setSubmissionDate(ControllerUtils.setValue(submitter.getSubmissionDate(), submitterDTO.getSubmissionDate()));

        submitter = submitterDao.save(submitter);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SubmitterDTO getSubmitterDTOById(Integer submitterId) {
	
		Submitter submitter = submitterDao.getById(submitterId);
		
		SubmitterConvertCriteriaDTO convertCriteria = new SubmitterConvertCriteriaDTO();
		return(this.convertSubmitterToSubmitterDTO(submitter,convertCriteria));
	}

}
