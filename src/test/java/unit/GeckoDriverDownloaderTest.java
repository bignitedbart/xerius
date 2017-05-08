package unit;

import be.vdab.drivers.DriverDownloader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class GeckoDriverDownloaderTest {

    @Test
    public void downloadBinary() throws Exception {
        File binary = new File("src/test/resources/geckodriver-v0.16.1-win32.zip");
        new DriverDownloader().downloadBinary("Firefox");
        Assert.assertNotNull(binary.exists());
    }

}