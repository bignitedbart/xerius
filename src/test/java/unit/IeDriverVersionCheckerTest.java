package unit;

import be.vdab.drivers.iedriver.IeDriverVersionChecker;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class IeDriverVersionCheckerTest {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Test @Ignore
    public void responseShouldNotBeNull() throws IOException {
        LOGGER.info("Checking version number ...");
        String version = new IeDriverVersionChecker().getVersion();
        LOGGER.info("Version number equals: " + version);
        assertNotNull("Version could not be retrieved!", version);
    }
}
