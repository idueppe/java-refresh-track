package com.lsy.vehicle.service;

import com.lsy.vehicle.dto.LogEntry;
import de.crowdcode.training.JNDI;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


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
    	logService.log("JUNIT LOG TEST");
        List<LogEntry> entries = logService.logEntries();
        assertNotNull(entries);
        assertTrue(entries.size() > 0);
    }
    
}
