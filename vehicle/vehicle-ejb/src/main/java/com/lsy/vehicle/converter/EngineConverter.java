package com.lsy.vehicle.converter;

import com.lsy.commons.converter.AbstractDefaultConverter;
import com.lsy.vehicle.domain.Engine;
import com.lsy.vehicle.dto.EngineDto;

import javax.ejb.Stateless;

/**
 * @author idueppe
 */
@Stateless
public class EngineConverter extends AbstractDefaultConverter<Engine, EngineDto>{

    @Override
    protected EngineDto newTargetInstance() {
        return new EngineDto();
    }

    @Override
    protected void copyProperties(Engine source, EngineDto target) {
        target.setEngineId(source.getId());
        target.setEngineType(source.getType().name());
    }

}
