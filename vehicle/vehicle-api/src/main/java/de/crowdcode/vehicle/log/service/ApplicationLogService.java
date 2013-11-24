package de.crowdcode.vehicle.log.service;

import de.crowdcode.vehicle.log.dto.LogEntry;

import java.util.List;

public interface ApplicationLogService {
    
    public void log(String message);
    
    public List<LogEntry> logEntries();

}
