package be.vdab.drivers.iedriver;

import be.vdab.drivers.DriverDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URISyntaxException;

public class IeDriverUpdater {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public void updateIeDriverToLatest() throws IOException, URISyntaxException {
        if(null != System.getProperty("useLatestIeDriver") || null != System.getProperty("useLatest")) {
            LOGGER.info("Downloading latest IEDriver ...");
            new DriverDownloader().downloadBinary("IE");
            LOGGER.info("Download successfull. Unzipping ...");
            new IeDriverUnzipper().unzipIedriver();
            LOGGER.info("IEDriverServer.exe updated to latest version");
        }
    }
}
