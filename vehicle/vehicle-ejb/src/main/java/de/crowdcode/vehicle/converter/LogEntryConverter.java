package de.crowdcode.vehicle.converter;

import javax.ejb.Stateless;
import javax.inject.Named;

import de.crowdcode.commons.converter.AbstractDefaultConverter;
import de.crowdcode.vehicle.log.domain.ApplicationLog;
import de.crowdcode.vehicle.log.dto.LogEntry;

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
