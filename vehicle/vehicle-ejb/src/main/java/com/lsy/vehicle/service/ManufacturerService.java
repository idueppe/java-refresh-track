package com.lsy.vehicle.service;

import com.lsy.vehicle.domain.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    
    public List<Manufacturer> findAll();
    
    public void addManufacturer(String manufacturerName) throws ManufacturerAlreadyExistsException;
    
    public Manufacturer byName(String manufacturerName);

    public boolean doesManufacturerExists(String manufacturerName);

	public void delete(Manufacturer manufacturer);
    
}
