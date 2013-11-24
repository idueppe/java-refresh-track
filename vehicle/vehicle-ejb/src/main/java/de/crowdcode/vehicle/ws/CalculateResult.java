package de.crowdcode.vehicle.ws;

public class CalculateResult {

    private Double value;
    
    public CalculateResult(Double value) {
        super();
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    
}
