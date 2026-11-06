package com.fleetmanagementsystem.dao;

import java.util.List;
import java.util.Date;

import com.fleetmanagementsystem.dao.GenericDAO;
import com.fleetmanagementsystem.domain.Transaction;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionDAO extends GenericDAO<Transaction, Integer> {
  
	List<Transaction> findAll();
	


}

