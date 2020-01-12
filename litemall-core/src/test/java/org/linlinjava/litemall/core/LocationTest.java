package org.linlinjava.litemall.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.core.location.LocationService;
import org.linlinjava.litemall.core.location.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class LocationTest {

    private final Log logger = LogFactory.getLog(LocationTest.class);
    @Autowired
    private LocationService locationService;

    @Test
    public void testLocation() {

        String address = "北京市朝阳区阜通东大街6号";
        try {
            Location location= locationService.getLocation(address);
            logger.info(location.toPosition());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    @Test
    public void testDistance() {
        Location origin = new Location("116.481028","39.989643");
        Location destination = new Location("114.481028","39.989643");
        try {
            String jsonResult = locationService.calcDistance(origin, destination);
            logger.info(jsonResult);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
