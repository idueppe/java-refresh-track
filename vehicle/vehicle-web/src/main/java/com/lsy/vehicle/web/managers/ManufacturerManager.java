package com.lsy.vehicle.web.managers;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.dto.ManufacturerDto;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * @author idueppe
 * @since 1.0
 */
@ManagedBean()
@RequestScoped
public class ManufacturerManager {

    @EJB
    private ManufacturerController manufacturerController;
    private ManufacturerDto manufacturer;
    private String uniqueManufacturerName;

    public List<ManufacturerDto> getAllManufacturers() {
        return manufacturerController.allManufactures();
    }

    public ManufacturerDto getManufacturer() {
        if (manufacturer == null) {
            manufacturer = new ManufacturerDto();
        }
        return manufacturer;
    }

    public String getUniqueManufacturerName() {
        return uniqueManufacturerName;
    }

    public void validateManufacturerName(FacesContext context, UIComponent component, Object value) {
        if (doManufacturerNameExistis((String)value)) {
            ((UIInput) component).setValid(false);
            FacesMessage msg = new FacesMessage("Name existiert bereits.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(component.getClientId(), msg);
        } else {
            ((UIInput) component).setValid(true);
        }
    }

    private boolean doManufacturerNameExistis(String manufacturerName) {
        return manufacturerController.doManufacturerExists(manufacturerName);
    }

    public String addManufacturer() {
        manufacturerController.addManufacturer(manufacturer.getName());

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setDetail("Neuer Herrsteller " + manufacturer.getName() + " hinzugefügt.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "/views/manufacturers";
    }

    public String cancelAdding() {
        return "/views/manufacturers";
    }
}
