package com.lsy.vehicle.converter;

import com.lsy.vehicle.log.domain.ApplicationLog;
import com.lsy.vehicle.log.dto.LogEntry;

import javax.ejb.Stateless;

@Stateless
public class LogEntryConverter extends AbstractDefaultConverter<ApplicationLog, LogEntry>{

    @Override
    protected LogEntry newTargetInstance() {
        return new LogEntry();
    }

    @Override
    protected void copyProperties(ApplicationLog source, LogEntry target) {
        target.setMessage(source.getMessage());
        target.setTimeStamp(source.getTimestamp());
    }

}
