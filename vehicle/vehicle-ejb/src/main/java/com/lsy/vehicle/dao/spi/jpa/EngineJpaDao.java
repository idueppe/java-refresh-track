package com.lsy.vehicle.dao.spi.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lsy.vehicle.dao.EngineDao;
import com.lsy.vehicle.domain.Engine;

@Named
@Stateless
@Local(EngineDao.class)
public class EngineJpaDao implements EngineDao
{

	@Inject
	private EntityManager em;

	public EngineJpaDao()
	{
	}

	@Override
	public List<Engine> findAll()
	{
		TypedQuery<Engine> query = em.createQuery("SELECT e FROM Engine e", Engine.class);
		return query.getResultList();
	}

	@Override
	public Engine find(Long id)
	{
		return em.find(Engine.class, id);
	}

	@Override
	public void create(Engine entity)
	{
		em.persist(entity);
	}

	@Override
	public void delete(Engine entity)
	{
		em.remove(entity);
	}

	@Override
	public Engine update(Engine entity)
	{
		return em.merge(entity);
	}

}
