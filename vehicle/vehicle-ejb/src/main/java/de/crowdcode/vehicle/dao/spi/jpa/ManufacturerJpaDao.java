package de.crowdcode.vehicle.dao.spi.jpa;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.crowdcode.vehicle.dao.ManufacturerDao;
import de.crowdcode.vehicle.domain.EngineType;
import de.crowdcode.vehicle.domain.Manufacturer;

@Named
@Stateless
@Local(ManufacturerDao.class)
public class ManufacturerJpaDao implements ManufacturerDao
{

	@Inject
	private EntityManager em;

	@Override
	public List<Manufacturer> findAll()
	{
		TypedQuery<Manufacturer> query = em.createQuery("SELECT m FROM Manufacturer m", Manufacturer.class);
		return query.getResultList();
	}

	@Override
	public Manufacturer find(Long id)
	{
		return em.find(Manufacturer.class, id);
	}

	@Override
	public void create(Manufacturer manufacturer)
	{
		em.persist(manufacturer);
	}

	@Override
	public void delete(Manufacturer manufacturer)
	{
		em.remove(manufacturer);
	}

	@Override
	public Manufacturer update(Manufacturer manufacturer)
	{
		return em.merge(manufacturer);
	}

	@Override
	public Manufacturer findManufacturerByName(String name)
	{
		TypedQuery<Manufacturer> query = em.createNamedQuery(Manufacturer.FIND_BY_NAME, Manufacturer.class);
		query.setParameter("name", name);
		List<Manufacturer> manufacturers = query.getResultList();
		if (manufacturers.size() > 0)
		{
			return manufacturers.get(0);
		} else
		{
			return null;
		}
	}

	@Override
	public List<Manufacturer> findManufacturerWithEngineType(EngineType... engineType)
	{
		TypedQuery<Manufacturer> query = em.createNamedQuery(Manufacturer.FIND_WITH_ENGINETYPE, Manufacturer.class);
		query.setParameter("engineType", Arrays.asList(engineType));
		return query.getResultList();
	}

}
