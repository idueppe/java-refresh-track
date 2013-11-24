package de.crowdcode.vehicle.cdi.persistence.cdi;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.crowdcode.vehicle.cdi.persistence.annotations.VehiclePersistence;

public class VehiclePersistenceProducer
{

	@PersistenceContext(unitName = "vehicle-foundation")
	private EntityManager em;

	@Default
	@Produces
	@VehiclePersistence
	public EntityManager getEntityManager()
	{
		return em;
	}

}
