package com.lsy.vehicle.dao.spi.jpa;

import com.lsy.vehicle.dao.ManufacturerDao;
import com.lsy.vehicle.domain.Manufacturer;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Local(ManufacturerDao.class)
public class ManufacturerJpaDao implements ManufacturerDao {
    
    @PersistenceContext(name="vehicle-foundation")
    private EntityManager em;
    
    @Override
    public List<Manufacturer> findAll() {
        TypedQuery<Manufacturer> query = em.createQuery("SELECT m FROM Manufacturer m", Manufacturer.class);
        return query.getResultList();
    }

    @Override
    public Manufacturer find(Long id) {
        return em.find(Manufacturer.class, id);
    }

    @Override
    public void create(Manufacturer manufacturer) {
        em.persist(manufacturer);
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        em.remove(manufacturer);
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return em.merge(manufacturer);
    }

    @Override
    public Manufacturer findManufacturerByName(String name) {
        TypedQuery<Manufacturer> query = em.createNamedQuery("findManufacturerByName", Manufacturer.class);
        query.setParameter("name", name);
        List<Manufacturer> manufacturers = query.getResultList();
        if (manufacturers.size() > 0) {
            return manufacturers.get(0);
        } else {
            return null;
        }
    }

}
