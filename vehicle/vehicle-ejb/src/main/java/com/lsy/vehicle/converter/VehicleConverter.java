package com.lsy.vehicle.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.lsy.commons.converter.AbstractDefaultConverter;
import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.dto.VehicleDto;

@Named
@Stateless
public class VehicleConverter extends AbstractDefaultConverter<Vehicle, VehicleDto>
{

	@Inject
	private EngineConverter engineConverter;

	@Override
	protected VehicleDto newTargetInstance()
	{
		return new VehicleDto();
	}

	@Override
	protected void copyProperties(Vehicle source, VehicleDto target) throws ConversionException
	{
		target.setId(source.getId());
		target.setModelName(source.getModel());
		target.setConstructionDate(source.getConstructionDate());
		target.setManufacturerName(source.getManufacturer().getName());
		target.setEngine(engineConverter.convert(source.getEngine()));
	}
}
