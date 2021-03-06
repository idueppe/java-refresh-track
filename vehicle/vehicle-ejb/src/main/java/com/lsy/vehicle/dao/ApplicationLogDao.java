package com.lsy.vehicle.dao;

import java.util.List;

import com.lsy.vehicle.log.domain.ApplicationLog;

public interface ApplicationLogDao {

    public void log(ApplicationLog logEntry);
    
    public List<ApplicationLog> findAll();
}
