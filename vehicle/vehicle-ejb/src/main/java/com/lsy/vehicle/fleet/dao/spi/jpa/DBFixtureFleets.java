package com.lsy.vehicle.fleet.dao.spi.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.fleet.domain.Fleet;

@Singleton
public class DBFixtureFleets {
	
	public static final String COMPANY_NAME = "crowdcode";

    private static final Logger LOG = Logger.getLogger(DBFixtureFleets.class.getName());

    @PersistenceContext(unitName="vehicle-foundation")
    private EntityManager em;
    
    private List<Fleet> fleets = new ArrayList<>();

    private Fleet currentFleet;
    
    public void createDefaultDataInDatabase() {
    	LOG.info("Creating dummy data...");
        this.createFleet(COMPANY_NAME)
            .addVehicleToFleet("Veyron")
            .addVehicleToFleet("Veyron Diesel")
            .addVehicleToFleet("A4")
            .persistAll();
    }

    public DBFixtureFleets persistAll() {
        persistAll(fleets);
        return this;
    }
    
    private DBFixtureFleets clear() {
        fleets.clear();
        currentFleet = null;
        return null;
    }
    
    public DBFixtureFleets unlinkFleets() {
        for (Fleet fleet: fleets) {
            fleet.getVehicles().clear();
        }
        return this;
    }

    public DBFixtureFleets removeAll() {
        unlinkFleets();
        em.createQuery("DELETE FROM Fleet").executeUpdate();
        fleets.clear();
        clear();
        return this;
    }
    
    private void persistAll(List<?> entities) {
        for (Object entity : entities) {
            System.out.println("--- persisting : "+entity);
            em.persist(entity);
        }
    }
    
    public DBFixtureFleets createFleet(String companyName) {
        currentFleet = new Fleet();
        currentFleet.setCompanyName(companyName);
        fleets.add(currentFleet);
        return this;
    }
    
    public DBFixtureFleets addVehicleToFleet(String model) {
        TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v WHERE v.model = :model", Vehicle.class);
        query.setParameter("model", model);
        Vehicle vehicle = query.getSingleResult();
        currentFleet.getVehicles().add(vehicle);
        return this;
    }

    public void terminateAllActiveSessionInDB() {
        Query nativeQuery = em.createNativeQuery("SELECT pg_terminate_backend(procpid) FROM pg_stat_activity WHERE datname = 'vehicle-tmp'");
        nativeQuery.executeUpdate();
    }
    
}
