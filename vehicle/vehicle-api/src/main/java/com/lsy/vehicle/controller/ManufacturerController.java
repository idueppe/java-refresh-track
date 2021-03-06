package com.lsy.vehicle.controller;

import com.lsy.vehicle.dto.ManufacturerDto;

import java.util.List;

public interface ManufacturerController {

    public ManufacturerDto byName(String manufacturerName);
    
    public List<ManufacturerDto> allManufactures();
    
    public void addManufacturer(String manufacturerName);
    
    public void deleteManufacturer(String manufacturerName);

    boolean doManufacturerExists(String manufacturerName);

    public void update(ManufacturerDto selectedManufacturer);
}
