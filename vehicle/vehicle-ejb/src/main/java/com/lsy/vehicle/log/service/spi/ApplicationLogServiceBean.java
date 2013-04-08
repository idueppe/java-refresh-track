package com.lsy.vehicle.log.service.spi;

import com.lsy.vehicle.converter.LogEntryConverter;
import com.lsy.vehicle.dao.ApplicationLogDao;
import com.lsy.vehicle.log.domain.ApplicationLog;
import com.lsy.vehicle.log.dto.LogEntry;
import com.lsy.vehicle.log.service.ApplicationLogService;

import javax.ejb.*;
import java.util.List;

@Stateless
@Remote(ApplicationLogService.class)
public class ApplicationLogServiceBean implements ApplicationLogService {

    @EJB
    private ApplicationLogDao dao;
    
    @EJB
    private LogEntryConverter logEntryConverter;
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void log(String message) {
    	System.out.println("MESSAGE "+message);
        dao.log(new ApplicationLog(message));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<LogEntry> logEntries() {
        return logEntryConverter.convert(dao.findAll());
    }
    
}
