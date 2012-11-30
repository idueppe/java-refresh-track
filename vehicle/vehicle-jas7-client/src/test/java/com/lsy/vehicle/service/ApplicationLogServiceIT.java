package com.lsy.vehicle.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.lsy.vehicle.dto.LogEntry;

import de.crowdcode.training.JNDI;


public class ApplicationLogServiceIT {
    
    private ApplicationLogService logService;

    @Before
    public void setUp() throws NamingException {
        logService = (ApplicationLogService) JNDI.lookup(JNDI.JBOSS_ENV, "vehicle-ear/ApplicationLogServiceBean/remote");
    }
    
    @Test
    public void testLogging() {
        logService.log("JUNIT LOG TEST");
    }
    
    @Test
    public void testLogEntries() {
        List<LogEntry> entries = logService.logEntries();
        assertNotNull(entries);
        for (LogEntry entry : entries) {
            System.out.println(entry);
        }
    }
    
}
