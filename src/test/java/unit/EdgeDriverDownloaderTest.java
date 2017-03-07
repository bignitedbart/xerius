package unit;


import be.vdab.DriverDownloader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class EdgeDriverDownloaderTest {

    @Test
    public void downloadBinary() throws Exception {
        File binary = new File("src/test/resources/edge.zip");
        new DriverDownloader().downloadBinary("edge");
        Assert.assertNotNull(binary.exists());
    }
}
