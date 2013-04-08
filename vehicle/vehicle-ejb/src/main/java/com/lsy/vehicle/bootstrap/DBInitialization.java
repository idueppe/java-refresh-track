package com.lsy.vehicle.bootstrap;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.lsy.vehicle.dao.spi.jpa.DBFixture;
import com.lsy.vehicle.fleet.dao.spi.jpa.DBFixtureFleets;
import com.lsy.vehicle.security.dao.spi.DBFixtureUser;

@Singleton
@Startup
public class DBInitialization {
	
	private static final Logger LOG = Logger.getLogger(DBInitialization.class.getName());
	
	@EJB
	DBFixture dbFixture;
	
	@EJB
	DBFixtureFleets dbFixtureFleets;
	
	@EJB
	DBFixtureUser dbFixtureUser;
	
	@PostConstruct
	public void initializeDatabase() {
	    LOG.info("-------------- Initializing Database with Dummy Data ----------------");
	    
	    dbFixture.createDefaultDataInDatabase();
	    dbFixtureFleets.createDefaultDataInDatabase();
	    dbFixtureUser.createDefaultDataInDatabase();
	}

    
}
