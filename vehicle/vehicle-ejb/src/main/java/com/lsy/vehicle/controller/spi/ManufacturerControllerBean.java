package com.lsy.vehicle.controller.spi;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.controller.ManufacturerControllerRemote;
import com.lsy.vehicle.converter.ManufacturerConverter;
import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.service.ManufacturerAlreadyExistsException;
import com.lsy.vehicle.service.ManufacturerService;

@Named
@Stateless
@Local(ManufacturerController.class)
@Remote(ManufacturerControllerRemote.class)
public class ManufacturerControllerBean implements ManufacturerController, ManufacturerControllerRemote
{

	private static final Logger LOG = Logger.getLogger(ManufacturerControllerBean.class.getName());

	@Inject
	private ManufacturerService manufacturerService;

	@Inject
	private ManufacturerConverter manufacturerConverter;

	@Override
	public ManufacturerDto byName(String manufacturerName)
	{
		Manufacturer manufacturer = manufacturerService.byName(manufacturerName);
		return manufacturerConverter.convert(manufacturer);
	}

	@Override
	public List<ManufacturerDto> allManufactures()
	{
		return manufacturerConverter.convert(manufacturerService.findAll());
	}

	@Override
	public void addManufacturer(String manufacturerName)
	{
		try
		{
			manufacturerService.addManufacturer(manufacturerName);
		} catch (ManufacturerAlreadyExistsException e)
		{
			LOG.log(Level.SEVERE, "Manufactuer " + manufacturerName + " allready exists.", e);
		}
	}

	@Override
	public void deleteManufacturer(String manufacturerName)
	{
		Manufacturer manufacturer = manufacturerService.byName(manufacturerName);
		if (manufacturer != null)
		{
			manufacturerService.delete(manufacturer);
		}
	}

	@Override
	public boolean doManufacturerExists(String manufacturerName)
	{
		return manufacturerService.doesManufacturerExists(manufacturerName);
	}

	@Override
	public void update(ManufacturerDto manufacturer)
	{
		manufacturerService.updateManufacturerName(manufacturer.getId(), manufacturer.getName());
	}

}
