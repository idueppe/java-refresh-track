package com.lsy.vehicle.log.service;

import com.lsy.vehicle.log.dto.LogEntry;

import java.util.List;

public interface ApplicationLogService {
    
    public void log(String message);
    
    public List<LogEntry> logEntries();

}
