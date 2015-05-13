package ${groupId};

import java.util.ResourceBundle;

/**
 *
 * 2014-aug-12
 * @author Anders Recksén (recan)
 */
public class Resources {

    private static final ResourceBundle RB = ResourceBundle.getBundle("main");
    
    /*
     * Browser att använda anges som en flagga i maven (mvn ... -D browser=<något>).
     * Default anges i propertyfilen.
     */
    public static final String BROWSER = System.getProperty("browser", RB.getString("BROWSER"));
    
    public static final String URL = RB.getString("URL");
}
