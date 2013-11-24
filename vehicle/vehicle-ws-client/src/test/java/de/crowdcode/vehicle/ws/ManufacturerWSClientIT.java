package de.crowdcode.vehicle.ws;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.crowdcode.vehicle.ws.ManufacturerType;
import de.crowdcode.vehicle.ws.ManufacturerWSBean;
import de.crowdcode.vehicle.ws.ManufacturerWSBeanService;

public class ManufacturerWSClientIT {

    @Test
    public void test() {
        ManufacturerWSBeanService service = new ManufacturerWSBeanService();
        ManufacturerWSBean bean = service.getManufacturerWSBeanPort();
        List<ManufacturerType> listAll = bean.listAll();
        System.out.println(Arrays.toString(listAll.toArray()));
        
    }

}
