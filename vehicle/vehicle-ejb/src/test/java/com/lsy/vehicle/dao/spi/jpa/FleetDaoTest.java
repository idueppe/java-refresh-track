package com.lsy.vehicle.dao.spi.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.lsy.vehicle.domain.Fleet;


@RunWith(MockitoJUnitRunner.class)
public class FleetDaoTest {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("junit-test-vehicle-foundation");
    
    @Spy
    private EntityManager em = emf.createEntityManager();
    
    @InjectMocks
    private FleetJpaDao dao;
    
    @InjectMocks
    private DBFixture dbFixture;
    
    @Before
    public void setUp() {
        dbFixture.createDefaultDataInDatabase();
    }

    @Test
    public void testPersitFleet() throws Exception {
        em.getTransaction().begin();
        Fleet fleet = new Fleet();
        dao.create(fleet);
        assertNotNull(fleet.getId());
        em.getTransaction().commit();
    }
    
    @Test
    public void testGetAllCompanyNames() throws Exception {
        List<String> names = dao.findAllCompanyNames();
        assertNotNull(names);
        assertFalse(names.isEmpty());
        assertEquals("crowdcode", names.get(0));
    }
    
}
