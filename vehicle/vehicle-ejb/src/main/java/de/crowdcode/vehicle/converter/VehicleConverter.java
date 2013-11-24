package de.crowdcode.vehicle.converter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import de.crowdcode.commons.converter.AbstractDefaultConverter;
import de.crowdcode.vehicle.domain.Vehicle;
import de.crowdcode.vehicle.dto.VehicleDto;

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
