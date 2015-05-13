package se.recan.framework.utils;

import org.apache.log4j.Logger;
import org.junit.Test;
import se.recan.framework.po.PersonPO;
import se.recan.framework.selenium.DriverFactory;

/**
 * 2015-apr-30
 *
 * @author Anders Recksén (andrec, Prolore)
 */
public class ManageWindowTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Test
    public void testManageWindow() {
        PersonPO person = new PersonPO();

//        DriverFactory.manageWindow(DriverFactory.Position.CORNER);
//        Helper.sleep();
        
//        DriverFactory.manageWindow(DriverFactory.Position.CENTER);
//        Helper.sleep();
        
//        DriverFactory.manageWindow(DriverFactory.Position.FULL);
//        Helper.sleep();
        
//        DriverFactory.manageWindow(DriverFactory.Position.LEFT);
//        Helper.sleep();
        
        DriverFactory.manageWindow(DriverFactory.Position.RIGHT);
//        Helper.sleep();   
        
//        person.cleanUp();
    }

}
