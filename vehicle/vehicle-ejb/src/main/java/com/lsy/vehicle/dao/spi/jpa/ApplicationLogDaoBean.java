package com.lsy.vehicle.dao.spi.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lsy.vehicle.dao.ApplicationLogDao;
import com.lsy.vehicle.log.domain.ApplicationLog;

@Named
@Stateless
@Local(ApplicationLogDao.class)
public class ApplicationLogDaoBean implements ApplicationLogDao
{

	@Inject
	private EntityManager em;

	@Override
	public void log(ApplicationLog logEntry)
	{
		em.persist(logEntry);
		em.flush();
	}

	@Override
	public List<ApplicationLog> findAll()
	{
		TypedQuery<ApplicationLog> query = em.createNamedQuery("ApplicationLog.LOAD_ALL", ApplicationLog.class);
		return query.getResultList();
	}

}