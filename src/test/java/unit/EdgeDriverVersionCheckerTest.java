package unit;

import be.vdab.drivers.edgedriver.EdgeDriverVersionChecker;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class EdgeDriverVersionCheckerTest {

    @Test
    public void getVersion() throws IOException {
        EdgeDriverVersionChecker EdgeDriverVersionChecker = new EdgeDriverVersionChecker();
            System.out.println(EdgeDriverVersionChecker.getVersion());
            Assert.assertNotNull(EdgeDriverVersionChecker.getVersion());
    }
}
