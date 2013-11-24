package de.crowdcode.vehicle.security.dao.spi.jpa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import de.crowdcode.vehicle.dao.spi.jpa.DBFixture;
import de.crowdcode.vehicle.fleet.dao.spi.jpa.DBFixtureFleets;
import de.crowdcode.vehicle.security.dao.spi.DBFixtureUser;
import de.crowdcode.vehicle.security.dao.spi.FleetGroupDaoBean;
import de.crowdcode.vehicle.security.domain.FleetGroup;
import de.crowdcode.vehicle.security.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class FleetGroupDaoTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("junit-test-vehicle-foundation");
    
    @Spy
    private EntityManager em = emf.createEntityManager();
    
    @InjectMocks
    private FleetGroupDaoBean dao;
    
    @InjectMocks
    private DBFixture dbFixture;
    
    @InjectMocks
    private DBFixtureFleets dbFixtureFleets;
    
    @InjectMocks
    private DBFixtureUser dbFixtureUser;
    
    @Before
    public void setUp() {
        em.getTransaction().begin();
        dbFixture.createDefaultDataInDatabase();
        dbFixtureFleets.createDefaultDataInDatabase();
        dbFixtureUser.createDefaultDataInDatabase();
        em.getTransaction().commit();
    }
    
    @After
    public void tearDown() {
        em.getTransaction().begin();
        dbFixtureUser.removeAll();
        dbFixtureFleets.removeAll();
        dbFixture.removeAll();
        em.getTransaction().commit();
    }
    
    @Test
    public void testFindGroupByCompanyName() {
        FleetGroup group = dao.findGroupByCompanyName("crowdcode");
        assertNotNull("Dao should find the group by companyName crowdcode",group);
        List<User> users = group.getUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
        
    }

    
}
