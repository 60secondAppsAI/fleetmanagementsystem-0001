package com.fleetmanagementsystem.dao;

import java.util.List;
import java.util.Date;

import com.fleetmanagementsystem.dao.GenericDAO;
import com.fleetmanagementsystem.domain.Submitter;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface SubmitterDAO extends GenericDAO<Submitter, Integer> {
  
	List<Submitter> findAll();
	


}

