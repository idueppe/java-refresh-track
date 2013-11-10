package com.lsy.vehicle.ws;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ManufacturerWSClientIT {

    @Test
    public void test() {
        ManufacturerWSBeanService service = new ManufacturerWSBeanService();
        ManufacturerWSBean bean = service.getManufacturerWSBeanPort();
        List<ManufacturerType> listAll = bean.listAll();
        System.out.println(Arrays.toString(listAll.toArray()));
        
    }

}
