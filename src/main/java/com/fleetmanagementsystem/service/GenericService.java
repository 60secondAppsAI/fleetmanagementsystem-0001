package com.fleetmanagementsystem.service;

import com.fleetmanagementsystem.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}