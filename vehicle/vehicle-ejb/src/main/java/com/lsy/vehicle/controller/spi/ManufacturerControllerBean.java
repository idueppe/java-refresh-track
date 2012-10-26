package com.lsy.vehicle.controller.spi;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.converter.ManufacturerConverter;
import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.service.ManufacturerService;

@Stateless
@Local(ManufacturerController.class)
public class ManufacturerControllerBean implements ManufacturerController {
    
    @EJB
    private ManufacturerService manufacturerService;
    
    @EJB
    private ManufacturerConverter manufacturerConverter;

    @Override
    public ManufacturerDto byName(String manufacturerName) {
        Manufacturer manufacturer = manufacturerService.byName(manufacturerName);
        return manufacturerConverter.convert(manufacturer);
    }

    @Override
    public List<ManufacturerDto> allManufactures() {
        // TODO Bitte implementiert diese Methode.
        return null;
    }

    @Override
    public void addManufacturer(String manufacturerName) {
        // TODO Bitte implementiert diese Methode.
    }

    @Override
    public void deleteManufacturer(String manufacturerName) {
        // TODO Bitte implementiert diese Methode.
    }

}
