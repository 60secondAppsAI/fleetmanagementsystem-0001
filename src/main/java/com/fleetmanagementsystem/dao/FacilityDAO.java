package com.fleetmanagementsystem.dao;

import java.util.List;
import java.util.Date;

import com.fleetmanagementsystem.dao.GenericDAO;
import com.fleetmanagementsystem.domain.Facility;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface FacilityDAO extends GenericDAO<Facility, Integer> {
  
	List<Facility> findAll();
	


}

