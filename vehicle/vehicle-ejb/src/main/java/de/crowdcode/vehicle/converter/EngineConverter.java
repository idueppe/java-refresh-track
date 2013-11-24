package de.crowdcode.vehicle.converter;

import javax.ejb.Stateless;
import javax.inject.Named;

import de.crowdcode.commons.converter.AbstractDefaultConverter;
import de.crowdcode.vehicle.domain.Engine;
import de.crowdcode.vehicle.dto.EngineDto;

/**
 * @author idueppe
 */
@Named
@Stateless
public class EngineConverter extends AbstractDefaultConverter<Engine, EngineDto>
{

	@Override
	protected EngineDto newTargetInstance()
	{
		return new EngineDto();
	}

	@Override
	protected void copyProperties(Engine source, EngineDto target)
	{
		target.setEngineId(source.getId());
		target.setEngineType(source.getType().name());
	}

}
