package com.lsy.vehicle.controller.spi;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.converter.ManufacturerConverter;
import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.service.ManufacturerAlreadyExistsException;
import com.lsy.vehicle.service.ManufacturerService;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless()
@Remote(ManufacturerController.class)
public class ManufacturerControllerBean implements ManufacturerController {
	
	private static final Logger LOG = Logger.getLogger(ManufacturerControllerBean.class.getName());
    
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
        return manufacturerConverter.convert(manufacturerService.findAll());
    }

    @Override
    public void addManufacturer(String manufacturerName) {
    	try {
			manufacturerService.addManufacturer(manufacturerName);
		} catch (ManufacturerAlreadyExistsException e) {
			LOG.log(Level.SEVERE, "Manufactuer "+manufacturerName+" allready exists.", e);
		}
    }

    @Override
    public void deleteManufacturer(String manufacturerName) {
    	Manufacturer manufacturer = manufacturerService.byName(manufacturerName);
    	if (manufacturer != null) {
    		manufacturerService.delete(manufacturer);
    	}
    }

    @Override
    public boolean doManufacturerExists(String manufacturerName) {
        return manufacturerService.doesManufacturerExists(manufacturerName);
    }

}
