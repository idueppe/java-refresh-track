package com.lsy.vehicle.dao.spi.jpa;

import com.lsy.vehicle.dao.FleetDao;
import com.lsy.vehicle.domain.Fleet;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Local(FleetDao.class)
public class FleetJpaDao implements FleetDao {

	@PersistenceContext(name="vehicle-foundation")
	private EntityManager em;
	
	@Override
	public List<Fleet> findAll() {
		return null;
	}

	@Override
	public Fleet find(Long id) {
		return em.find(Fleet.class, id);
	}

	@Override
	public void create(Fleet entity) {
		em.persist(entity);
	}

	@Override
	public void delete(Fleet entity) {
		em.remove(entity);
	}

	@Override
	public Fleet update(Fleet entity) {
		return em.merge(entity);
	}

	@Override
	public Fleet findByCompanyName(String companyName) {
		TypedQuery<Fleet> query = em.createNamedQuery("fleetByCompanyName",Fleet.class);
		query.setParameter("companyName", companyName);
		
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
