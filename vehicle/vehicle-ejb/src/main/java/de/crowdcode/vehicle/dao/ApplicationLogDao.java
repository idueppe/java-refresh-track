package de.crowdcode.vehicle.dao;

import java.util.List;

import de.crowdcode.vehicle.log.domain.ApplicationLog;

public interface ApplicationLogDao {

    public void log(ApplicationLog logEntry);
    
    public List<ApplicationLog> findAll();
}
