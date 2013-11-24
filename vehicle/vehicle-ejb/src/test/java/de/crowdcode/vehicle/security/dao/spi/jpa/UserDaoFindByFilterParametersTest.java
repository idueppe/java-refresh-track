package de.crowdcode.vehicle.security.dao.spi.jpa;

import static org.junit.Assert.assertEquals;
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
import de.crowdcode.vehicle.security.dao.spi.UserDaoBean;
import de.crowdcode.vehicle.security.domain.Role;
import de.crowdcode.vehicle.security.domain.User;
import de.crowdcode.vehicle.security.filter.UserColumn;
import de.crowdcode.vehicle.security.filter.UserFilterParameters;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoFindByFilterParametersTest {

    private static EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("junit-test-vehicle-foundation");

    @Spy
    private EntityManager em = emf.createEntityManager();

    @InjectMocks
    private UserDaoBean dao;

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
    public void testFindAdmin() {
        UserFilterParameters params = new UserFilterParameters();
        params.getColumn(UserColumn.USERNAME).setFilter("admin");
        params.getColumn(UserColumn.EMAIL).setFilter("admin@junit");
        params.getColumn(UserColumn.ROLE).setFilter(Role.ADMIN.toString());
        List<User> users = dao.findByFilter(params);
        
        assertNotNull("Users must not be NULL.", users);
        assertFalse("Users should not be empty.", users.isEmpty());
        assertEquals(1, users.size());
        assertEquals("admin", users.get(0).getUsername());
        assertEquals("admin@junit", users.get(0).getEmail());
        assertEquals(Role.ADMIN, users.get(0).getRole());
    }

    @Test
    public void testFindAllByEmail() {
        UserFilterParameters params = new UserFilterParameters();
        params.getColumn(UserColumn.EMAIL).setFilter("@junit");
        List<User> users = dao.findByFilter(params);

        assertEquals(3, users.size());
    }

    @Test
    public void testFindAllByEmailSearchingAdd() {
        UserFilterParameters params = new UserFilterParameters();
        params.getColumn(UserColumn.EMAIL).setFilter("@");
        List<User> users = dao.findByFilter(params);

        assertEquals(3, users.size());
    }

    @Test
    public void testFindAll() {
        List<User> users = dao.findByFilter(new UserFilterParameters());
        assertEquals(3, users.size());
    }
    
    @Test
    public void testFindAllWithOrder() {
        UserFilterParameters params = new UserFilterParameters();
        params.getColumn(UserColumn.USERNAME).toggleOrder(); // ASCENDING
        params.getColumn(UserColumn.USERNAME).toggleOrder(); // DESCENDING
        List<User> users = dao.findByFilter(params);
        assertEquals(3, users.size());
        assertEquals("customer", users.get(0).getUsername());
    }
    
    @Test
    public void testFindCustomers() {
        UserFilterParameters params = new UserFilterParameters();
        params.getColumn(UserColumn.ROLE).setFilter(Role.CUSTOMER.toString());
        
        List<User> users = dao.findByFilter(params);
        assertEquals(1, users.size());
        assertEquals(Role.CUSTOMER, users.get(0).getRole());
    }

    
}
