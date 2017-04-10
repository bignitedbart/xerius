package be.vdab.edgedriver;

import be.vdab.DriverDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class EdgeDriverUpdater {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public void updateEdgeDriverToLatest() throws IOException, URISyntaxException {
        if(null != System.getProperty("useLatestEdgeDriver") || null != System.getProperty("useLatest")) {
            LOGGER.info("Downloading latest edgedriver ...");
            new DriverDownloader().downloadBinary("Edge");
            LOGGER.info("microsoftwebdriver.exe updated to latest version");
        }
    }
}
