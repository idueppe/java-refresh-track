package com.lsy.vehicle.converter;

import javax.ejb.Stateless;
import javax.inject.Named;

import com.lsy.commons.converter.AbstractDefaultConverter;
import com.lsy.vehicle.log.domain.ApplicationLog;
import com.lsy.vehicle.log.dto.LogEntry;

@Named
@Stateless
public class LogEntryConverter extends AbstractDefaultConverter<ApplicationLog, LogEntry>
{

	@Override
	protected LogEntry newTargetInstance()
	{
		return new LogEntry();
	}

	@Override
	protected void copyProperties(ApplicationLog source, LogEntry target)
	{
		target.setMessage(source.getMessage());
		target.setTimeStamp(source.getTimestamp());
	}

}
