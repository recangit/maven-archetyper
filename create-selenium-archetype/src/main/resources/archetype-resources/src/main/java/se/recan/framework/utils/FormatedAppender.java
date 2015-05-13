package se.recan.framework.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 2015-maj-02
 * @author Anders Recksén (recan, Prolore)
 */
public class FormatedAppender extends FileAppender {

    private File file;
    private String trim;
    private String pattern;
    
    @Override
    public void append(LoggingEvent event) {
        String message = null;
        if(event.locationInformationExists()){
            StringBuilder formatedMessage = new StringBuilder();
            formatedMessage.append(event.getLocationInformation().getClassName());
            formatedMessage.append(".");
            formatedMessage.append(event.getLocationInformation().getMethodName());
            formatedMessage.append(":");
            formatedMessage.append(event.getLocationInformation().getLineNumber());
            formatedMessage.append(" - ");
            formatedMessage.append(event.getMessage().toString());
            message = formatedMessage.toString();
        }else{
            message = event.getMessage().toString();
        }

        
        super.append(event);
}

    @Override
    public void setFile(String logFile) {
        file = new File(logFile);
    }
    
    private void writeFile(String value) {
        PrintWriter out = null;
        try {
           out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.file, true), Charset.forName("UTF8"))));
            out.println(value);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
