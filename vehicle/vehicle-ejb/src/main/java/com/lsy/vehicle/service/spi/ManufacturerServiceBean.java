package com.lsy.vehicle.service.spi;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.lsy.vehicle.dao.ManufacturerDao;
import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.service.ManufacturerAlreadyExistsException;
import com.lsy.vehicle.service.ManufacturerService;

@Named @Stateless(mappedName = "ManufacturerService")
@Local(ManufacturerService.class)
public class ManufacturerServiceBean implements ManufacturerService
{

	@Inject
	private ManufacturerDao manuDao;

	@Override
	public List<Manufacturer> findAll()
	{
		return manuDao.findAll();
	}

	@Override
	public void addManufacturer(String manufacturerName) throws ManufacturerAlreadyExistsException
	{
		if (doesManufacturerExists(manufacturerName))
		{
			throw new ManufacturerAlreadyExistsException(manufacturerName);
		}
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(manufacturerName);
		manuDao.create(manufacturer);
	}

	public boolean doesManufacturerExists(String manufacturerName)
	{
		return manuDao.findManufacturerByName(manufacturerName) != null;
	}

	@Override
	public Manufacturer byName(String manufacturerName)
	{
		return manuDao.findManufacturerByName(manufacturerName);
	}

	@Override
	public void delete(Manufacturer manufacturer)
	{
		manuDao.delete(manufacturer);
	}

	@Override
	public void updateManufacturerName(Long id, String name)
	{
		Manufacturer manufacturer = manuDao.find(id);
		manufacturer.setName(name);
		// manuDao.update(manufacturer); Optional für nicht magie
	}

}
