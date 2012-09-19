package com.lsy.vehicle.converter;

import javax.ejb.Stateless;

import com.lsy.vehicle.domain.ApplicationLog;
import com.lsy.vehicle.dto.LogEntry;

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
