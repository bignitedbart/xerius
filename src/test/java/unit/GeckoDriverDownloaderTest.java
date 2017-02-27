package unit;

import be.vdab.geckodriver.GeckoDriverDownloader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class GeckoDriverDownloaderTest {

    @Test
    public void downloadBinary() throws Exception {
        File binary = new File("src/test/resources/geckodriver-v0.14.0-win32.zip");
        new GeckoDriverDownloader().downloadBinary();
        Assert.assertNotNull(binary.exists());
    }

}