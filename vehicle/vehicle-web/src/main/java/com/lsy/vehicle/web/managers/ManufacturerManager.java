package com.lsy.vehicle.web.managers;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.dto.ManufacturerDto;

/**
 * @author idueppe
 * @since 1.0
 */
@Named
@SessionScoped
public class ManufacturerManager implements Serializable
{

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(ManufacturerManager.class.getName());

	@Inject
	private ManufacturerController manufacturerController;

	@Inject
	private VehiclesManager vehiclesManager;

	private ManufacturerDto selectedManufacturer;
	private String uniqueManufacturerName;

	public List<ManufacturerDto> getAllManufacturers()
	{
		return manufacturerController.allManufactures();
	}

	public ManufacturerDto getManufacturer()
	{
		if (selectedManufacturer == null)
		{
			selectedManufacturer = new ManufacturerDto();
		}
		return selectedManufacturer;
	}

	public String getUniqueManufacturerName()
	{
		return uniqueManufacturerName;
	}

	public void validateManufacturerName(FacesContext context, UIComponent component, Object value)
	{
		if (value != null && doManufacturerNameExistis((String) value))
		{
			((UIInput) component).setValid(false);
			FacesMessage msg = new FacesMessage("Name existiert bereits.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(component.getClientId(), msg);
		} else
		{
			((UIInput) component).setValid(true);
		}
	}

	private boolean doManufacturerNameExistis(String manufacturerName)
	{
		ManufacturerDto found = manufacturerController.byName(manufacturerName);
		// TODO mach lesbar mach richtig!
		if (found != null && found.getId() != null)
		{
			return !found.getId().equals(selectedManufacturer.getId());
		} else
		{
			return false;
		}
	}

	public String selectForUpdateManufacturer(ManufacturerDto manufacturer)
	{
		LOG.info("------- " + manufacturer.getName() + " ----------- SELECTED");
		this.selectedManufacturer = manufacturerController.byName(manufacturer.getName());
		return "/views/addmanufacturer";
	}

	public String updateManufacturer()
	{
		manufacturerController.update(selectedManufacturer);
		return "/views/manufacturers";
	}

	public String addManufacturer()
	{
		manufacturerController.addManufacturer(selectedManufacturer.getName());

		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary("Neuer Herrsteller " + selectedManufacturer.getName() + " hinzugefügt.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return "/views/manufacturers";
	}

	public String cancelAdding()
	{
		return "/views/manufacturers";
	}

	public String startAddingNewManufacturer()
	{
		selectedManufacturer = new ManufacturerDto();
		return "/views/addmanufacturer";
	}

	public VehiclesManager getVehiclesManager()
	{
		return vehiclesManager;
	}

	public void setVehiclesManager(VehiclesManager vehiclesManager)
	{
		this.vehiclesManager = vehiclesManager;
	}

	public boolean isEditing()
	{
		return (selectedManufacturer.getId() != null);
	}
}
