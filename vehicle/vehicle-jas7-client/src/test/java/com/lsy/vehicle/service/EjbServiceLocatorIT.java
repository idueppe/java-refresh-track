package com.lsy.vehicle.service;

import com.lsy.vehicle.log.dto.LogEntry;
import com.lsy.vehicle.log.service.ApplicationLogService;

import de.crowdcode.training.jbossas.EjbServiceLocator;
import org.junit.Test;

import javax.naming.NamingException;

import static org.junit.Assert.assertNotNull;


public class EjbServiceLocatorIT {
    
    @Test
    public void testServiceLocation() throws Exception {
    	try {
    		ApplicationLogService service = EjbServiceLocator.lookupService("vehicle-ear", "vehicle-ejb", null, "ApplicationLogServiceBean", ApplicationLogService.class);
    		assertNotNull(service);
    		for (LogEntry entry : service.logEntries()) {
    			System.out.println(entry.getMessage());
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    }
    
    @Test
    public void testServiceLocator2() throws NamingException {
    	ApplicationLogService logService = EjbServiceLocator.lookupRemoteStatefulCounter();
    	logService.log("FOUNT");
    }
    
}
