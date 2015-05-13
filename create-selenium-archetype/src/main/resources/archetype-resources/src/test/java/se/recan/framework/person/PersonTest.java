package se.recan.framework.person;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import se.recan.framework.po.PersonPO;
import se.recan.framework.utils.ScreenShot;

/**
 *
 * 2015-jan-24
 *
 * @author Anders Recksén (recan)
 */
public class PersonTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    
    private static PersonPO person;
    
    @Test
    public void testFirstForm() throws Exception {
        Assert.assertEquals("personer", person.getTitle());
        Assert.assertEquals("En rubrik", person.getHeadline());
        
        person.setFirstName("Anders");
        person.setLastName("Recksén");
        person.setSocialNumb("6120024318");
        person.setUserName2("Namn 2");
        person.setUserName("Namn 1");
        person.setPassword1("12345");
        person.setPassword2("12345");
        person.setGender(PersonPO.FEMALE);
        person.setSkill(PersonPO.SOLDIER);
        person.setSkill(PersonPO.TINKER);
        person.select("detective");
        person.select(3);
        person.setDescription("En text");
        Assert.assertTrue("Inskrivna uppgifter validerar inte!", person.validate());
        person.sleep();
        
        LOGGER.debug(person.getResult());
    }
    
    @Test
    public void testSecondForm() throws Exception {
        person.setFirstNameAgain("Kajsa");
        person.setLastNameAgain("Annie");
        person.sleep(3);
    }
    
//    @Test
    public void testScreenshot() {
        ScreenShot.captureScreenshot("PersonTest");
    }
    
    @BeforeClass
    public static void before() {
        person = new PersonPO();
    }
    
    @AfterClass
    public static void after() {
        person.cleanUp();
    }
}
