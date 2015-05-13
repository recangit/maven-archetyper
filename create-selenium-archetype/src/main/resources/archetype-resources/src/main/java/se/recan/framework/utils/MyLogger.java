package se.recan.framework.utils;

import java.util.Formatter;
import org.apache.log4j.Logger;

public class MyLogger {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private String fmt = "%s";

    public MyLogger() {
    }

    public MyLogger(String fmt) {
        this.fmt = fmt;
    }

    public void setFmt(String fmt) {
        this.fmt = fmt;
    }

    public void removeFmt() {
        this.fmt = "%s";
    }

    public void debug(String... parameters) {

        StringBuffer buffer = new StringBuffer();
        Formatter formatter = new Formatter(buffer);
        formatter.format(fmt, parameters);

        LOGGER.debug(buffer);
    }
}
