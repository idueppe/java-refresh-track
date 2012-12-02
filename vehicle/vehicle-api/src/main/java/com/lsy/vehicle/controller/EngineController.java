package com.lsy.vehicle.controller;

import com.lsy.vehicle.dto.EngineDto;

import java.util.List;

public interface EngineController {

    public List<EngineDto> byManufacturerName(String manufacturerName);
    
    public void addEngine(EngineDto engine);
    
    public void deleteEngine(EngineDto engine);
    
}
