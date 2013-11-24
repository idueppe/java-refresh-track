package de.crowdcode.vehicle.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="calucateDoc")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculateInput {

    @XmlElement(name="value")
    private List<Double> values;

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
    
}
