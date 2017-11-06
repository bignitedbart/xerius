package unit;

import be.biginted.drivers.geckodriver.GeckoDriverVersionChecker;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class GeckoDriverVersionCheckerTest {

    @Test
    public void getVersion() throws IOException {
        GeckoDriverVersionChecker geckoDriverVersionChecker = new GeckoDriverVersionChecker();
        Assert.assertNotNull(geckoDriverVersionChecker.getVersion());
    }
}
