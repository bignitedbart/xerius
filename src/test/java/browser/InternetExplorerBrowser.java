package browser;

import be.vdab.chromedriver.ChromeDriverUpdater;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.ProxyManager;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class InternetExplorerBrowser implements Browser {

    private RemoteWebDriver remoteWebDriver;
    private InternetExplorerDriverService internetexplorerDriverService;
    private ProxyManager proxyManager;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public InternetExplorerBrowser() throws IOException, URISyntaxException {
        proxyManager = new ProxyManager();
        new ChromeDriverUpdater().updateChromeDriverToLatest();
        setDriver();
    }

    private void buildInternetExplorerDriverService() {
        if (null == internetexplorerDriverService) {
            LOGGER.info("Building ChromeDriver service ...");
            internetexplorerDriverService = new InternetExplorerDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File("src/test/resources/IEDriverServer.exe"))
                    .build();
        } else {
            LOGGER.info("Internet explorer driver service already set!");
        }
    }

    private void startIEDriverService() {
        if (null != internetexplorerDriverService && !internetexplorerDriverService.isRunning()) {
            try {
                LOGGER.info("Starting ChromeDriver service ...");
                internetexplorerDriverService.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDriver() {
        if (null == internetexplorerDriverService) {
            LOGGER.info("RemoteWebDriver not set yet, I'll build and start it for you.");
            buildInternetExplorerDriverService();
            startIEDriverService();
        }
        if (null == remoteWebDriver) {
            remoteWebDriver = new RemoteWebDriver(internetexplorerDriverService.getUrl(), buildDesiredCapabilities());
            maximizeBrowserWindow(remoteWebDriver);
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
        if (null != internetexplorerDriverService && internetexplorerDriverService.isRunning()) {
            internetexplorerDriverService.stop();
        }
    }

    private DesiredCapabilities buildDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if(null != System.getProperty("browsermob")) {
            desiredCapabilities = proxyManager.getDesiredCapabilities();
        }
        desiredCapabilities.setBrowserName("ie");
        return desiredCapabilities;
    }

    private void maximizeBrowserWindow(RemoteWebDriver driver) {
        driver.manage().window().maximize();
    }
}
