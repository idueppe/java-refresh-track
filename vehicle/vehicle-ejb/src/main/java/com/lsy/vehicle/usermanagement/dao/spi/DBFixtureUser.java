package com.lsy.vehicle.usermanagement.dao.spi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.lsy.vehicle.fleet.domain.Fleet;
import com.lsy.vehicle.usermanagement.domain.FleetGroup;
import com.lsy.vehicle.usermanagement.domain.Role;
import com.lsy.vehicle.usermanagement.domain.User;

@Singleton
public class DBFixtureUser {
	
	private static final Logger LOG = Logger.getLogger(DBFixtureUser.class.getName());

    @PersistenceContext(unitName="vehicle-foundation")
    private EntityManager em;
    
    private List<User> users = new ArrayList<User>();
    private List<FleetGroup> groups = new ArrayList<FleetGroup>();

    public User currentUser;
    private FleetGroup currentGroup;
    
    public void createDefaultDataInDatabase() {
    	LOG.info("Creating user data...");
        this.createUser("admin", "admin@junit")
            .setRole(Role.ADMIN)
            .createUser("agent", "agent@junit")
            .setRole(Role.AGENT)
            .createUser("customer", "customer@junit")
            .setRole(Role.CUSTOMER)
            .createFleetGroup()
            .addFleet("crowdcode")
            .addUser(0)
            .addUser(1)
            .addUser(2)
            .persistAll();
    }

    public DBFixtureUser persistAll() {
        persistAll(users);
        persistAll(groups);
        return this;
    }
    
    private DBFixtureUser clear() {
        users.clear();
        groups.clear();
        return this;
    }
    
    public DBFixtureUser removeAll() {
        List<FleetGroup> groups = em.createQuery("SELECT g FROM FleetGroup g", FleetGroup.class).getResultList();
        for (FleetGroup group : groups) {
            group.getUsers().clear();
            group.setFleet(null);
            em.flush();
        }
        
        
        em.createQuery("DELETE FROM FleetGroup").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();
        clear();
        return this;
    }
    
    private void persistAll(List<?> entities) {
        for (Object entity : entities) {
            System.out.println("--- persisting : "+entity);
            em.persist(entity);
        }
    }
    
    public DBFixtureUser createUser(String username, String email) {
        currentUser = new User();
        currentUser.setUsername(username);
        currentUser.setEmail(email);
        users.add(currentUser);
        return this;
    }
    
    public DBFixtureUser setRole(Role role)  {
        currentUser.setRole(role);
        return this;
    }
    
    public DBFixtureUser createFleetGroup() {
        currentGroup = new FleetGroup();
        groups.add(currentGroup);
        return this;
    }
    
    public DBFixtureUser addFleet(String companyName) {
        TypedQuery<Fleet> query = em.createNamedQuery(Fleet.FIND_BY_COMPANY_NAME, Fleet.class);
        query.setParameter("companyName", companyName);
        currentGroup.setFleet(query.getSingleResult());
        return this;
    }
    
    public DBFixtureUser addUser(int index) {
        currentGroup.getUsers().add(users.get(index));
        return this;
    }
    
    public void terminateAllActiveSessionInDB() {
        Query nativeQuery = em.createNativeQuery("SELECT pg_terminate_backend(procpid) FROM pg_stat_activity WHERE datname = 'vehicle-tmp'");
        nativeQuery.executeUpdate();
    }
    

}
