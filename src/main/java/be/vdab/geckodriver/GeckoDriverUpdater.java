package be.vdab.geckodriver;

import be.vdab.DriverDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class GeckoDriverUpdater {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public void updateGeckoDriverToLatest() throws IOException, URISyntaxException {
        if(null != System.getProperty("useLatestGeckoDriver") || null != System.getProperty("useLatest")) {
            LOGGER.info("Downloading latest geckodriver ...");
            new DriverDownloader().downloadBinary("Firefox");
            LOGGER.info("Download successfull. Unzipping ...");
            new GeckoDriverUnzipper().unzipGeckodriver();
            LOGGER.info("GeckoDriver.exe updated to latest version");
        }
    }
}
