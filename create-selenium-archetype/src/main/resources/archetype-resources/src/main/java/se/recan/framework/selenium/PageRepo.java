package se.recan.framework.selenium;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * 2015-apr-25
 * @author Anders Recksén (recan, Prolore)
 */
public class PageRepo {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private static final Map<String, String> PAGE_MAP = new HashMap<>();
    
    private static final String USER_DIR = System.getProperty("user.dir");
    
    static {
        PAGE_MAP.put("PersonPO", "file:///" + USER_DIR + "/docs/person.html");
    }
    
    public static String page(String p) {
        System.out.println("HTML "+"file:///" + USER_DIR + "/docs/person.html");
        return PAGE_MAP.get(p);
    }
    
}
