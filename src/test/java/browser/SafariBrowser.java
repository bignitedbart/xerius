package browser;


import be.biginted.drivers.chromedriver.ChromeDriverUpdater;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ProxyManager;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class SafariBrowser implements Browser {

    private ProxyManager proxyManager;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private RemoteWebDriver remoteWebDriver;
    private SafariDriverService safariDriverService;

    public SafariBrowser() throws IOException, URISyntaxException {
        proxyManager = new ProxyManager();
        new ChromeDriverUpdater().updateChromeDriverToLatest();
        setDriver();
    }

    private void buildSafariDriverService ()throws IOException {
        if (null == safariDriverService) {
            LOGGER.info("Building ChromeDriver service ...");
            safariDriverService = new SafariDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File("src/test/resources/chromedriver.exe"))
                    .build();
        } else {
            LOGGER.info("ChromeDriver service already set!");
        }
    }

    private void startChromeDriverService() {
        if (null != safariDriverService && !safariDriverService.isRunning()) {
            try {
                LOGGER.info("Starting ChromeDriver service ...");
                safariDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }










    @Override
    public void setDriver() {

    }

    @Override
    public WebDriver getDriver() {
        return null;
    }

    @Override
    public void killDriver() {

    }
}
