package de.crowdcode.vehicle.security.dao.spi.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

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

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("junit-test-vehicle-foundation");
    
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
    public void testCreateUnitUser() {
        em.getTransaction().begin();
        User user = buildUser(Role.AGENT);
        dao.create(user);
        assertNotNull(user.getId());
        em.getTransaction().commit();
    }

    @Test
    public void testFindDummyUsers() {
        em.getTransaction().begin();
        
        User admin = dao.findByUsername("admin");
        assertEquals(Role.ADMIN, admin.getRole());
        
        User agent = dao.findByUsername("agent");
        assertEquals(Role.AGENT, agent.getRole());
        
        User customer = dao.findByUsername("customer");
        assertEquals(Role.CUSTOMER, customer.getRole());
        
        em.getTransaction().rollback();
    }
    
    @Test
    public void testFindNotMembers() {
        User user = buildUser(Role.CUSTOMER);

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        
        List<User> users = dao.findAllCustomersNotMemberOfCompany(DBFixtureUser.COMPANY_NAME);
        assertNotNull(users);
        assertFalse(users.isEmpty());
        
        assertEquals(user, users.get(0));
    }

    @Test
    public void testFindNotMembersForNotExistingCompany() {
        List<User> users = dao.findAllCustomersNotMemberOfCompany("DOES-NOT-EXISTS-"+UUID.randomUUID());
        assertNotNull(users);
        assertTrue(users.isEmpty());
    }
    
    @Test
    public void testFindByRole() {
        List<User> customers = dao.findAllOfRole(Role.CUSTOMER);
        for (User user : customers) {
            assertEquals(Role.CUSTOMER, user.getRole());
        }
    }
    
    private User buildUser(Role role) {
        User user = new User();
        user.setFirstname("J");
        user.setSurename("Unit");
        user.setUsername("junit");
        user.setEmail("j@unit");
        user.setRole(role);
        return user;
    }
    

    
}
