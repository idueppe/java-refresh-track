package com.lsy.vehicle.dao;

import com.lsy.vehicle.log.domain.ApplicationLog;

import java.util.List;

public interface ApplicationLogDao {

    public void log(ApplicationLog logEntry);
    
    public List<ApplicationLog> findAll();
}
