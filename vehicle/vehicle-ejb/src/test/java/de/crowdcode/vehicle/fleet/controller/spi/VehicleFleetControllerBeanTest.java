package de.crowdcode.vehicle.fleet.controller.spi;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.crowdcode.vehicle.domain.Vehicle;
import de.crowdcode.vehicle.fleet.controller.spi.VehicleFleetControllerBean;
import de.crowdcode.vehicle.fleet.converter.FleetVehicleDtoConverter;
import de.crowdcode.vehicle.fleet.domain.Fleet;
import de.crowdcode.vehicle.fleet.dto.FleetVehicleDto;
import de.crowdcode.vehicle.fleet.service.FleetService;
import de.crowdcode.vehicle.service.VehicleService;

@RunWith(MockitoJUnitRunner.class)
public class VehicleFleetControllerBeanTest {
    
    @InjectMocks
    private VehicleFleetControllerBean vehicleFleetController;
    
    @Mock
    private FleetService fleetService;
    
    @Mock
    private VehicleService vehicleService;
    
    @Mock
    private FleetVehicleDtoConverter converter;
    
    @Test
    public void testGetVehicleByName() throws Exception {
        // when
        when(fleetService.findFleetByName("companyName")).thenReturn(new Fleet());
        
        // given
        List<FleetVehicleDto> result = vehicleFleetController
                        .getVehicleFleetByName("companyName");
        // then
        assertNotNull(result);
        verify(converter,times(1)).convert(anyListOf(Vehicle.class));
    }
    
    /** 
     * Nicht testen, was passiert wenn fleet.getVehicles() null zurück gibt.
     * Dies sollte bei Fleet getestet werden, dass immer eine Liste zurück gegeben wird.
     */
    @Test
    public void testGetVehicleByNameWithEmptyFleet() throws Exception {
        // given
        Fleet fleet = Mockito.mock(Fleet.class);
        when(fleet.getVehicles()).thenReturn(null);
        when(fleetService.findFleetByName("companyName")).thenReturn(new Fleet());
        
        // when
        List<FleetVehicleDto> result = vehicleFleetController
                        .getVehicleFleetByName("companyName");
        // then
        assertNotNull(result);
        verify(converter,times(1)).convert(anyListOf(Vehicle.class));
    }
    
    @Test
    public void testAddVehicle() throws Exception {
        // given
        List<FleetVehicleDto> vehicleList = new LinkedList<>();
        FleetVehicleDto fleetVehicleDto = new FleetVehicleDto();
        fleetVehicleDto.setVehicleId(1L);
        
        vehicleList.add(fleetVehicleDto);
        
        when(vehicleService.getVehicleById(1l)).thenReturn(new Vehicle());
        
        // when
        vehicleFleetController.addVehicles("companyName", vehicleList);
        
        // then
//      FIXME idueppe - assert is broken...
//        verify(fleetService, times(1) )
//           .addVehicles(eq("companyName"), argThat(new VehicleListMatcher(1L)));
        
        
    }
    
    public static class VehicleListMatcher extends ArgumentMatcher<List<Vehicle>>  
    {
        private Long expectedId;

        public VehicleListMatcher(Long expectedId) {
            super();
            this.expectedId = expectedId;
        }

        @Override
        public boolean matches(Object argument) {
            if (argument instanceof List)
            {
                @SuppressWarnings("unchecked")
                List<Vehicle> vehicles = (List<Vehicle>) argument;
                return vehicles.get(0).getId() == expectedId;
            }
            return false;
        }

    }
    

}
