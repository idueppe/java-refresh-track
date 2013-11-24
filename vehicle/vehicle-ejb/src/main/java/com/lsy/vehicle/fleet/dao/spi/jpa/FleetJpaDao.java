package com.lsy.vehicle.fleet.dao.spi.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.lsy.vehicle.fleet.dao.FleetDao;
import com.lsy.vehicle.fleet.domain.EngineInfo;
import com.lsy.vehicle.fleet.domain.Fleet;

@Named
@Stateless
@Local(FleetDao.class)
public class FleetJpaDao implements FleetDao
{

	@Inject
	private EntityManager em;

	@Override
	public List<Fleet> findAll()
	{
		TypedQuery<Fleet> query = em.createNamedQuery(Fleet.FIND_ALL, Fleet.class);
		return query.getResultList();
	}

	@Override
	public Fleet find(Long id)
	{
		return em.find(Fleet.class, id);
	}

	@Override
	public void create(Fleet entity)
	{
		em.persist(entity);
	}

	@Override
	public void delete(Fleet entity)
	{
		em.remove(entity);
	}

	@Override
	public Fleet update(Fleet entity)
	{
		return em.merge(entity);
	}

	@Override
	public Fleet findByCompanyName(String companyName)
	{
		TypedQuery<Fleet> query = em.createNamedQuery(Fleet.FIND_BY_COMPANY_NAME, Fleet.class);
		query.setParameter("companyName", companyName);

		try
		{
			return query.getSingleResult();
		} catch (NoResultException ex)
		{
			return null;
		}
	}

	@Override
	public List<String> findAllCompanyNames()
	{
		TypedQuery<String> query = em.createNamedQuery(Fleet.FIND_ALL_COMPANY_NAMES, String.class);
		return query.getResultList();
	}

	public List<EngineInfo> getEngineReport(String companyName)
	{
		TypedQuery<EngineInfo> query = em.createNamedQuery(Fleet.ENGINE_REPORT, EngineInfo.class);
		query.setParameter("companyName", companyName);

		return query.getResultList();
	}

	public List<?> getEngineReport1(String companyName)
	{
		Query query = em.createNamedQuery(Fleet.ENGINE_REPORT_V1);
		query.setParameter("companyName", companyName);
		return query.getResultList();
	}

	public List<EngineInfo> getEngineReport2(String companyName)
	{
		TypedQuery<EngineInfo> query = em.createNamedQuery(Fleet.ENGINE_REPORT_V2, EngineInfo.class);
		query.setParameter("vehicles", findByCompanyName(companyName).getVehicles());
		return query.getResultList();
	}

}
