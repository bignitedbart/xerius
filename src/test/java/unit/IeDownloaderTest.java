package unit;

import be.vdab.drivers.DriverDownloader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class IeDownloaderTest {

    @Test
    public void downloadBinary() throws Exception {
        File binary = new File("src/test/resources/IEDriverServer_Win32_3.2.0.zip");
        new DriverDownloader().downloadBinary("IE");
        Assert.assertNotNull(binary.exists());
    }

}