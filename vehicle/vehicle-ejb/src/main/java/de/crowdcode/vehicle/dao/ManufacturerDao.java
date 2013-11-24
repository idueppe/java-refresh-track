package de.crowdcode.vehicle.dao;

import java.util.List;

import de.crowdcode.vehicle.domain.EngineType;
import de.crowdcode.vehicle.domain.Manufacturer;

public interface ManufacturerDao extends EntityDao<Manufacturer> {
    
    public Manufacturer findManufacturerByName(String name);
    
    public List<Manufacturer> findManufacturerWithEngineType(EngineType... engineType);

}
