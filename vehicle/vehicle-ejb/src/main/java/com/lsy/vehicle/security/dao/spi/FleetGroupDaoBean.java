package com.lsy.vehicle.security.dao.spi;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lsy.vehicle.fleet.domain.Fleet;
import com.lsy.vehicle.security.dao.FleetGroupDao;
import com.lsy.vehicle.security.domain.FleetGroup;

import de.crowdcode.vehicle.cdi.persistence.annotations.VehiclePersistence;

@Named
@Stateless
@Local(FleetGroupDao.class)
public class FleetGroupDaoBean implements FleetGroupDao
{

	@Inject
	@VehiclePersistence
	private EntityManager em;

	@Override
	public FleetGroup findGroup(Long id)
	{
		return em.find(FleetGroup.class, id);
	}

	@Override
	public void create(FleetGroup group)
	{
		em.persist(group);
	}

	@Override
	public void update(FleetGroup group)
	{
		em.merge(group);
	}

	@Override
	public void remove(FleetGroup group)
	{
		em.remove(group);
	}

	@Override
	public FleetGroup findGroupByFleet(Fleet fleet)
	{
		TypedQuery<FleetGroup> query = em.createNamedQuery(FleetGroup.FIND_BY_FLEET, FleetGroup.class);
		query.setParameter("fleet", fleet);
		return query.getSingleResult();
	}

	@Override
	public FleetGroup findGroupByCompanyName(String companyName)
	{
		TypedQuery<FleetGroup> query = em.createNamedQuery(FleetGroup.FIND_BY_COMPANYNAME, FleetGroup.class);
		query.setParameter("companyName", companyName);
		return query.getSingleResult();
	}

}
