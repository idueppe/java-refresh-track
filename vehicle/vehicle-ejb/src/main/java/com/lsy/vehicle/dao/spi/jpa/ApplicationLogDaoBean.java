package com.lsy.vehicle.dao.spi.jpa;

import com.lsy.vehicle.dao.ApplicationLogDao;
import com.lsy.vehicle.domain.ApplicationLog;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Local(ApplicationLogDao.class)
public class ApplicationLogDaoBean implements ApplicationLogDao {

    @PersistenceContext(unitName="vehicle-foundation")
    private EntityManager em;

    @Override
    public void log(ApplicationLog logEntry) {
        em.persist(logEntry);
        em.flush();
    }

    @Override
    public List<ApplicationLog> findAll() {
        TypedQuery<ApplicationLog> query = em.createNamedQuery("ApplicationLog.LOAD_ALL", ApplicationLog.class);
        return query.getResultList();
    }
    
}