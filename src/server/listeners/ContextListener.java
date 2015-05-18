package server.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Samuel on 1-5-2015.
 */
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger logger = Logger.getLogger("atd-web_logger");
        try {
            FileHandler fh = new FileHandler("atd-web_logs.txt");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        logger.info("ATD Web service started\n");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Logger.getLogger("atd-web_logger").info("ATD Web service stopped\n");
    }
}
