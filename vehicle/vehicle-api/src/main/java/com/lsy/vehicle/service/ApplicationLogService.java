package com.lsy.vehicle.service;

import com.lsy.vehicle.dto.LogEntry;

import java.util.List;

public interface ApplicationLogService {
    
    public void log(String message);
    
    public List<LogEntry> logEntries();

}
