package de.crowdcode.vehicle.controller;

import de.crowdcode.vehicle.dto.EngineDto;

import java.util.List;

public interface EngineController {

    public List<EngineDto> byManufacturerName(String manufacturerName);
    
    public void addEngine(EngineDto engine);
    
    public void deleteEngine(EngineDto engine);
    
}
