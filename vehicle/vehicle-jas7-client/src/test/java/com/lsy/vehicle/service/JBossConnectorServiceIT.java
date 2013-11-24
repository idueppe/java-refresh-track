package de.crowdcode.vehicle.service;

import de.crowdcode.vehicle.log.dto.LogEntry;
import de.crowdcode.vehicle.log.service.ApplicationLogService;

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
