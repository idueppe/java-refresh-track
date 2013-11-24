package de.crowdcode.vehicle.bootstrap;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;

import de.crowdcode.vehicle.dao.spi.jpa.DBFixture;
import de.crowdcode.vehicle.fleet.dao.spi.jpa.DBFixtureFleets;
import de.crowdcode.vehicle.security.dao.spi.DBFixtureUser;

@Named
@Startup
@Singleton
public class DBInitialization
{

	private static final Logger LOG = Logger.getLogger(DBInitialization.class.getName());

	@Inject
	private DBFixture dbFixture;

	@Inject
	private DBFixtureFleets dbFixtureFleets;

	@Inject
	private DBFixtureUser dbFixtureUser;

	@PostConstruct
	public void initializeDatabase()
	{
		LOG.info("-------------- Initializing Database with Dummy Data ----------------");

		dbFixture.createDefaultDataInDatabase();
		dbFixtureFleets.createDefaultDataInDatabase();
		dbFixtureUser.createDefaultDataInDatabase();
	}

}
