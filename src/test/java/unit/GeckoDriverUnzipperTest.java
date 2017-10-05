package unit;

import be.vdab.utilities.Unzipper;
import org.junit.Test;

public class GeckoDriverUnzipperTest {

    @Test
    public void unzipBinary() throws Exception {
        Unzipper unzipper = new Unzipper();
        unzipper.unZipIt("src/test/resources/geckodriver-v0.17.0-win32.zip", "src/test/resources/");
    }
}
