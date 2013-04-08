package com.lsy.vehicle.converter;

import com.lsy.commons.converter.AbstractDefaultConverter;
import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.dto.ManufacturerDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManufacturerConverter extends AbstractDefaultConverter<Manufacturer, ManufacturerDto>{

    @EJB
    private VehicleConverter vehicleConverter;

    @Override
    protected ManufacturerDto newTargetInstance() {
        return new ManufacturerDto();
    }

    @Override
    protected void copyProperties(Manufacturer source, ManufacturerDto target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setVehicles(vehicleConverter.convert(source.getVehicles()));
    }

}
