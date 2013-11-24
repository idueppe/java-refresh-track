package de.crowdcode.vehicle.dao.spi.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import de.crowdcode.vehicle.dao.spi.jpa.DBFixture;
import de.crowdcode.vehicle.dao.spi.jpa.ManufacturerJpaDao;
import de.crowdcode.vehicle.domain.EngineType;
import de.crowdcode.vehicle.domain.Manufacturer;
import de.crowdcode.vehicle.fleet.dao.spi.jpa.DBFixtureFleets;


@RunWith(MockitoJUnitRunner.class)
public class ManufacturerDaoTest {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("junit-test-vehicle-foundation");
    
    @Spy
    private EntityManager em = emf.createEntityManager();
    
    @InjectMocks
    private ManufacturerJpaDao dao;
    
    @InjectMocks
    private DBFixture dbFixture;
    
    @InjectMocks
    private DBFixtureFleets dbFixtureFleets;
    
    @Before
    public void setUp() {
        em.getTransaction().begin();
        dbFixture.createDefaultDataInDatabase();
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
    public void testWithEngineType() {
        List<Manufacturer> found = dao.findManufacturerWithEngineType(EngineType.PETROL, EngineType.DIESEL);
        printList(found);
        
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Manufacturer> criteriaQuery = builder.createQuery(Manufacturer.class);
        
        Metamodel metamodel = em.getMetamodel();
        EntityType<Manufacturer> manufacturer_ = metamodel.entity(Manufacturer.class);
        
        Root<Manufacturer> from = criteriaQuery.from(manufacturer_);
        Path<Object> name = from.get("name");
        Predicate equal = builder.equal(name, builder.parameter(String.class, "name"));
        criteriaQuery.where(equal);
        
        TypedQuery<Manufacturer> query = em.createQuery(criteriaQuery);
        query.setParameter("name", "Buggati");
        printList(query.getResultList());
    }

    private void printList(List<?> found) {
        System.out.println("------------------------------");
        System.out.println(Arrays.toString(found.toArray()));
        System.out.println("------------------------------");
    }

}
