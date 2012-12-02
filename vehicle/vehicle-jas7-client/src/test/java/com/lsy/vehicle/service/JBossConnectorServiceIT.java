package com.lsy.vehicle.service;

import com.lsy.vehicle.dto.LogEntry;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;


public class JBossConnectorServiceIT {
    
    private ApplicationLogService logService;
    
    @Test
    public void testLogEntries() throws ConnectionException {
    	JBossConnector connector = new JBossConnector();
    	logService = connector.getApplicationLogService();
    	
    	logService.log("Hello JBoss!");
    	
    	List<LogEntry> entries = logService.logEntries();
        assertNotNull(entries);
        for (LogEntry entry : entries) {
            System.out.println(entry);
        }
    }
    
}
