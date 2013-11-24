package com.lsy.vehicle.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.service.ManufacturerService;

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
