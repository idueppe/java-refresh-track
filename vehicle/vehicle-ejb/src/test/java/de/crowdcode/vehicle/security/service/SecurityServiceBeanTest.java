package de.crowdcode.vehicle.security.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.crowdcode.vehicle.fleet.dao.FleetDao;
import de.crowdcode.vehicle.security.dao.FleetGroupDao;
import de.crowdcode.vehicle.security.dao.UserDao;
import de.crowdcode.vehicle.security.domain.FleetGroup;
import de.crowdcode.vehicle.security.domain.User;
import de.crowdcode.vehicle.security.service.spi.SecurityServiceBean;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceBeanTest {
    
    @InjectMocks
    private SecurityServiceBean securityManager;
    
    @Mock
    private FleetGroupDao groupDao;
    
    @Mock
    private FleetDao fleetDao;
    
    @Mock
    private UserDao userDao; 
    
    @Test
    public void testGetFleetGroup() {
        // given
        when(groupDao.findGroupByCompanyName(anyString())).thenReturn(null);
        when(userDao.findByUsername("username")).thenReturn(new User());
        
        // when
        securityManager.addUserToGroup("companyName", "username");
        
        // then
        verify(groupDao, times(1)).create(any(FleetGroup.class));
        verify(groupDao, times(1)).update(any(FleetGroup.class));
    }
    
    @Test
    public void testAddUserToGroup() {
        // given
        FleetGroup group = new FleetGroup();
        User user = new User();

        when(groupDao.findGroupByCompanyName(anyString())).thenReturn(group);
        when(userDao.findByUsername("username")).thenReturn(user);
        
        // when
        securityManager.addUserToGroup("companyName", "username");
        
        // then
        verify(groupDao, never()).create(any(FleetGroup.class));
        verify(groupDao, times(1)).update(group);
        
        assertTrue(group.getUsers().contains(user));
    }

}
