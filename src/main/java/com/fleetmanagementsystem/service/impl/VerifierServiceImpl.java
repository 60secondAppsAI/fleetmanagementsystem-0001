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
import com.fleetmanagementsystem.dao.VerifierDAO;
import com.fleetmanagementsystem.domain.Verifier;
import com.fleetmanagementsystem.dto.VerifierDTO;
import com.fleetmanagementsystem.dto.VerifierSearchDTO;
import com.fleetmanagementsystem.dto.VerifierPageDTO;
import com.fleetmanagementsystem.dto.VerifierConvertCriteriaDTO;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import com.fleetmanagementsystem.service.VerifierService;
import com.fleetmanagementsystem.util.ControllerUtils;


@Service
public class VerifierServiceImpl extends GenericServiceImpl<Verifier, Integer> implements VerifierService {

    private final static Logger logger = LoggerFactory.getLogger(VerifierServiceImpl.class);

	@Autowired
	VerifierDAO verifierDao;

	

	@Override
	public GenericDAO<Verifier, Integer> getDAO() {
		return (GenericDAO<Verifier, Integer>) verifierDao;
	}
	
	public List<Verifier> findAll () {
		List<Verifier> verifiers = verifierDao.findAll();
		
		return verifiers;	
		
	}

	public ResultDTO addVerifier(VerifierDTO verifierDTO, RequestDTO requestDTO) {

		Verifier verifier = new Verifier();

		verifier.setVerifierId(verifierDTO.getVerifierId());

		verifier.setTaskId(verifierDTO.getTaskId());

		verifier.setVerifierUserId(verifierDTO.getVerifierUserId());

		verifier.setVerificationDate(verifierDTO.getVerificationDate());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		verifier = verifierDao.save(verifier);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Verifier> getAllVerifiers(Pageable pageable) {
		return verifierDao.findAll(pageable);
	}

	public Page<Verifier> getAllVerifiers(Specification<Verifier> spec, Pageable pageable) {
		return verifierDao.findAll(spec, pageable);
	}

	public ResponseEntity<VerifierPageDTO> getVerifiers(VerifierSearchDTO verifierSearchDTO) {
	
			Integer verifierId = verifierSearchDTO.getVerifierId(); 
   			Date verificationDate = verifierSearchDTO.getVerificationDate();
 			String sortBy = verifierSearchDTO.getSortBy();
			String sortOrder = verifierSearchDTO.getSortOrder();
			String searchQuery = verifierSearchDTO.getSearchQuery();
			Integer page = verifierSearchDTO.getPage();
			Integer size = verifierSearchDTO.getSize();

	        Specification<Verifier> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, verifierId, "verifierId"); 
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, verificationDate, "verificationDate");
			

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

		Page<Verifier> verifiers = this.getAllVerifiers(spec, pageable);
		
		//System.out.println(String.valueOf(verifiers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(verifiers.getTotalPages()));
		
		List<Verifier> verifiersList = verifiers.getContent();
		
		VerifierConvertCriteriaDTO convertCriteria = new VerifierConvertCriteriaDTO();
		List<VerifierDTO> verifierDTOs = this.convertVerifiersToVerifierDTOs(verifiersList,convertCriteria);
		
		VerifierPageDTO verifierPageDTO = new VerifierPageDTO();
		verifierPageDTO.setVerifiers(verifierDTOs);
		verifierPageDTO.setTotalElements(verifiers.getTotalElements());
		return ResponseEntity.ok(verifierPageDTO);
	}

	public List<VerifierDTO> convertVerifiersToVerifierDTOs(List<Verifier> verifiers, VerifierConvertCriteriaDTO convertCriteria) {
		
		List<VerifierDTO> verifierDTOs = new ArrayList<VerifierDTO>();
		
		for (Verifier verifier : verifiers) {
			verifierDTOs.add(convertVerifierToVerifierDTO(verifier,convertCriteria));
		}
		
		return verifierDTOs;

	}
	
	public VerifierDTO convertVerifierToVerifierDTO(Verifier verifier, VerifierConvertCriteriaDTO convertCriteria) {
		
		VerifierDTO verifierDTO = new VerifierDTO();

		verifierDTO.setVerifierId(verifier.getVerifierId());

		verifierDTO.setTaskId(verifier.getTaskId());

		verifierDTO.setVerifierUserId(verifier.getVerifierUserId());

		verifierDTO.setVerificationDate(verifier.getVerificationDate());
		
		return verifierDTO;
	}

	public ResultDTO updateVerifier(VerifierDTO verifierDTO, RequestDTO requestDTO) {
		
		Verifier verifier = verifierDao.getById(verifierDTO.getVerifierId());
		
		verifier.setVerifierId(ControllerUtils.setValue(verifier.getVerifierId(), verifierDTO.getVerifierId()));
		
		verifier.setTaskId(ControllerUtils.setValue(verifier.getTaskId(), verifierDTO.getTaskId()));
		
		verifier.setVerifierUserId(ControllerUtils.setValue(verifier.getVerifierUserId(), verifierDTO.getVerifierUserId()));
		
		verifier.setVerificationDate(ControllerUtils.setValue(verifier.getVerificationDate(), verifierDTO.getVerificationDate()));

        verifier = verifierDao.save(verifier);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public VerifierDTO getVerifierDTOById(Integer verifierId) {
	
		Verifier verifier = verifierDao.getById(verifierId);
		
		VerifierConvertCriteriaDTO convertCriteria = new VerifierConvertCriteriaDTO();
		return(this.convertVerifierToVerifierDTO(verifier,convertCriteria));
	}

}
