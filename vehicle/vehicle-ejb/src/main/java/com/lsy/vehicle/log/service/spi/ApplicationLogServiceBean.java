package com.lsy.vehicle.log.service.spi;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import com.lsy.vehicle.converter.LogEntryConverter;
import com.lsy.vehicle.dao.ApplicationLogDao;
import com.lsy.vehicle.log.domain.ApplicationLog;
import com.lsy.vehicle.log.dto.LogEntry;
import com.lsy.vehicle.log.service.ApplicationLogService;
import com.lsy.vehicle.log.service.ApplicationLogServiceRemote;

@Named
@Stateless
@Local(ApplicationLogService.class)
@Remote(ApplicationLogServiceRemote.class)
public class ApplicationLogServiceBean implements ApplicationLogService
{

	@Inject
	private ApplicationLogDao dao;

	@Inject
	private LogEntryConverter logEntryConverter;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void log(String message)
	{
		System.out.println("MESSAGE " + message);
		dao.log(new ApplicationLog(message));
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<LogEntry> logEntries()
	{
		return logEntryConverter.convert(dao.findAll());
	}

}
