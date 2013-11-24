package de.crowdcode.vehicle.security.dao;

import de.crowdcode.vehicle.fleet.domain.Fleet;
import de.crowdcode.vehicle.security.domain.FleetGroup;

public interface FleetGroupDao {

    public FleetGroup findGroup(Long id);
   
    public void create(FleetGroup group);
    
    public void update(FleetGroup group);
    
    public void remove(FleetGroup group);
    
    public FleetGroup findGroupByFleet(Fleet fleet);

    public FleetGroup findGroupByCompanyName(String string);

}
