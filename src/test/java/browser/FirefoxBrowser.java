package browser;

import be.vdab.drivers.geckodriver.GeckoDriverUpdater;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ProxyManager;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class FirefoxBrowser implements Browser {

    private RemoteWebDriver remoteWebDriver;
    private GeckoDriverService geckoDriverService;
    private ProxyManager proxyManager;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public FirefoxBrowser() throws IOException, URISyntaxException {
        proxyManager = new ProxyManager();
        new GeckoDriverUpdater().updateGeckoDriverToLatest();
        setDriver();
    }

    private void buildGeckoDriverService() {
        if (null == geckoDriverService) {
            LOGGER.info("Building GeckoDriver service ...");
            geckoDriverService = new GeckoDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File("src/test/resources/geckodriver.exe"))
                    .build();
        } else {
            LOGGER.info("GeckoDriver service already set!");
        }
    }

    private void startGeckoDriverService() {
        if (null != geckoDriverService && !geckoDriverService.isRunning()) {
            try {
                LOGGER.info("Starting GeckoDriver service ...");
                geckoDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDriver() {
        if (null == geckoDriverService) {
            LOGGER.info("RemoteWebDriver not set yet, I'll build and start it for you.");
            buildGeckoDriverService();
            startGeckoDriverService();
        }
        if (null == remoteWebDriver) {
            remoteWebDriver = new RemoteWebDriver(geckoDriverService.getUrl(), buildDesiredCapabilities());
            maximizeBrowserWindow();
        }
    }

    public RemoteWebDriver getDriver() {
        if (null == remoteWebDriver) {
            setDriver();
        }
        return remoteWebDriver;
    }

    public void killDriver() {
        if (null != remoteWebDriver) {
            remoteWebDriver.quit();
        }
        if (null != geckoDriverService && geckoDriverService.isRunning()) {
            geckoDriverService.stop();
        }
    }

    private DesiredCapabilities buildDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if(null != System.getProperty("browsermob")) {
            desiredCapabilities = proxyManager.getDesiredCapabilities();
        }
        desiredCapabilities.setBrowserName("internetExplorer");
        return desiredCapabilities;
    }

    private void maximizeBrowserWindow() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        remoteWebDriver.manage().window().maximize();
    }
}
