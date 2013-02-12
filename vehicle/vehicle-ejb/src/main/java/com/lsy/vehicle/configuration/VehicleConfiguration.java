package com.lsy.vehicle.configuration;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.lsy.vehicle.service.FleetService;
import com.lsy.vehicle.service.VehicleObserverRegistration;

@Singleton
@Startup
public class VehicleConfiguration {
    
    private static final Logger LOG = Logger.getLogger(VehicleConfiguration.class.getName());

    @EJB
    private FleetService fleetService;
    
    @EJB
    private VehicleObserverRegistration registration;
    
    @PostConstruct
    public void configurate() {
        LOG.info("Starting configurating observers");
        registration.addObserver(fleetService);
        LOG.info("Finished configuration.");
    }
    
}
