package de.crowdcode.vehicle.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService
public class CalculatorServiceBean {

    @WebMethod
    public int add(int a, int b) {
        return a + b;
    }

    public CalculateResult sum(CalculateInput values)
    {
        double sum = 0.0;
        for (Double value : values.getValues())
        {
            sum += value;
        }
       return new CalculateResult(sum);
    }
    
    public CalculateResult sumInts(Integer... values)
    {
        double sum = 0.0;
        for (Integer value : values)
        {
            sum += value;
        }
       return new CalculateResult(sum);
    }

    @WebMethod
    public double div(Double a, Double b) {
        return a / b;
    }

    @WebMethod(action = "intDiv", operationName = "divInt")
    @RequestWrapper(localName = "divIntRequest")
    @ResponseWrapper(localName = "divIntResponse")
    public int divInt(Integer a, Integer b) {
        return a / b;
    }

    public static void main(String... args) {
        CalculatorServiceBean service = new CalculatorServiceBean();
        Endpoint endpoint = Endpoint.publish("http://localhost:8080/calculator", service);

        JOptionPane.showConfirmDialog(null, "is running...");

        endpoint.stop();
    }
}