package de.crowdcode.vehicle.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;

import de.crowdcode.vehicle.controller.ManufacturerController;
import de.crowdcode.vehicle.domain.Manufacturer;
import de.crowdcode.vehicle.dto.ManufacturerDto;
import de.crowdcode.vehicle.service.ManufacturerService;

@WebService
@Named
@Stateless
public class ManufacturerWSBean
{

	@Inject
	private ManufacturerService manufacturerService;

	@Inject
	private ManufacturerController controller;

	@WebMethod
	public void updateManufacturerName(Long id, String name)
	{
		manufacturerService.updateManufacturerName(id, name);
	}

	@WebMethod
	public List<Manufacturer> listAll()
	{
		return manufacturerService.findAll();
	}

	@WebMethod
	public List<ManufacturerDto> listAllDtos()
	{
		return controller.allManufactures();
	}

}
