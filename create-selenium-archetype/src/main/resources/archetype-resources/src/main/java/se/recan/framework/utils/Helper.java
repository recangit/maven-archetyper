package se.recan.framework.utils;

/**
 * 2015-apr-30
 * @author Anders Recksén (andrec, Prolore)
 */
public class Helper {

    private static final int SLEEP = 1;
    
    public static void sleep() {
        sleep(SLEEP);
    }

    public static void sleep(long l) {
        if (l == 0) {
            return;
        }

        try {
            Thread.sleep(l * 1000);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
    }
}
