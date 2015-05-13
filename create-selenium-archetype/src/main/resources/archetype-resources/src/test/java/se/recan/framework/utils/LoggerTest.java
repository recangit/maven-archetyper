package se.recan.framework.utils;

import org.junit.Test;

/**
 * 2015-apr-30
 * @author Anders Recksén (andrec, Prolore)
 */
public class LoggerTest {

    // http://www.vogella.com/tutorials/Logging/article.html
    
//    private static final MyLogger LOGGER = new MyLogger("%s:%-12s:%s%n");
    private static final MyLogger LOGGER = new MyLogger();
    
//    @Test
    public void testMyLogger() {
        LOGGER.debug("A", "B", "C");
        
        String[] fmt = {
            "%3s:%s:%s%n",      // Flytta vänstermarginal
            "%6S:%s:%s%n",      // Flytta vänstermarginal
            "%s:%s:%s%n",       // Ingen förflyttning
            "%-5s%-5s%s%n",     // Minus flyttar nästkommande sträng till höger
            "%s:%-2s:%s%n",     // Minus flyttar nästkommande sträng till höger
            "%s:%-12s:%s%n",    // Minus flyttar nästkommande sträng till höger
            "%s:%12s:%s%n",     // Flyttar denna sträng till höger
            "   %s   %s   %s%n" // Kan också göras så här
        };

        for (String s : fmt) {
            LOGGER.setFmt(s);
            LOGGER.debug("A", "B", "C");
        }
    }
    
//    @Test
    public void testOrdning() {
        LOGGER.setFmt("%4$2s %3$2s %2$2s %1$2s");
        LOGGER.debug("A", "B", "C", "D");
    }
    
    @Test
    public void testNoFormat() {
//        LOGGER.removeFmt();
        LOGGER.debug("A", "B", "C", "D");
    }
}