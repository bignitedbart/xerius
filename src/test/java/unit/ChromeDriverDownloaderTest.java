package unit;

import be.biginted.utilities.DriverDownloader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ChromeDriverDownloaderTest {

    @Test
    public void downloadBinary() throws Exception {
        File binary = new File("src/test/resources/chromedriver.zip");
        new DriverDownloader().downloadBinary("Chrome");
        Assert.assertNotNull(binary.exists());
    }

}