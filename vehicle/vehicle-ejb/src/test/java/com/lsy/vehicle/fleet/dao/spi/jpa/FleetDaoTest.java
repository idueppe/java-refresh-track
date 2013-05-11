package com.lsy.vehicle.fleet.dao.spi.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
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

import com.lsy.vehicle.dao.spi.jpa.DBFixture;
import com.lsy.vehicle.domain.EngineType;
import com.lsy.vehicle.fleet.domain.EngineInfo;
import com.lsy.vehicle.fleet.domain.Fleet;

@RunWith(MockitoJUnitRunner.class)
public class FleetDaoTest {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("junit-test-vehicle-foundation");
    
    @Spy
    private EntityManager em = emf.createEntityManager();
    
    @InjectMocks
    private FleetJpaDao dao;
    
    @InjectMocks
    private DBFixture dbFixture;
    
    @InjectMocks
    private DBFixtureFleets dbFixtureFleets;
    
    @Before
    public void setUp() {
        em.getTransaction().begin();
        dbFixture.createDefaultDataInDatabase();
        em.getTransaction().commit();
        em.getTransaction().begin();
        dbFixtureFleets.createDefaultDataInDatabase();
        em.getTransaction().commit();
    }
    
    @After
    public void tearDown() {
        em.getTransaction().begin();
        dbFixtureFleets.removeAll();
        dbFixture.removeAll();
        em.getTransaction().commit();
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
    
    @Test
    public void testEngineReport() throws Exception {
        List<EngineInfo> report = dao.getEngineReport(DBFixtureFleets.COMPANY_NAME);
        assertNotNull(report);
        assertFalse(report.isEmpty());
        System.out.println(Arrays.toString(report.toArray()));
        assertEquals(EngineType.PETROL, report.get(0).getEngineType());
        assertEquals(1L, report.get(0).getQuantity());
        assertEquals(EngineType.DIESEL, report.get(1).getEngineType());
        assertEquals(2L, report.get(1).getQuantity());
    }
    
}
