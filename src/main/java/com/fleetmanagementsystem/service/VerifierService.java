package com.fleetmanagementsystem.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.fleetmanagementsystem.domain.Verifier;
import com.fleetmanagementsystem.dto.VerifierDTO;
import com.fleetmanagementsystem.dto.VerifierSearchDTO;
import com.fleetmanagementsystem.dto.VerifierPageDTO;
import com.fleetmanagementsystem.dto.VerifierConvertCriteriaDTO;
import com.fleetmanagementsystem.service.GenericService;
import com.fleetmanagementsystem.dto.common.RequestDTO;
import com.fleetmanagementsystem.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface VerifierService extends GenericService<Verifier, Integer> {

	List<Verifier> findAll();

	ResultDTO addVerifier(VerifierDTO verifierDTO, RequestDTO requestDTO);

	ResultDTO updateVerifier(VerifierDTO verifierDTO, RequestDTO requestDTO);

    Page<Verifier> getAllVerifiers(Pageable pageable);

    Page<Verifier> getAllVerifiers(Specification<Verifier> spec, Pageable pageable);

	ResponseEntity<VerifierPageDTO> getVerifiers(VerifierSearchDTO verifierSearchDTO);
	
	List<VerifierDTO> convertVerifiersToVerifierDTOs(List<Verifier> verifiers, VerifierConvertCriteriaDTO convertCriteria);

	VerifierDTO getVerifierDTOById(Integer verifierId);



	
}
