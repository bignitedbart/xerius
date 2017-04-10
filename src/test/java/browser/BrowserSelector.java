package browser;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * Created by cleenma on 2/2/2017.
 */

public class BrowserSelector{

    private RemoteWebDriver remoteWebDriver;
    // Declareer binnen Edge loop / Declareer 1 voor elke browser binnen zijn eigen loop.
    private ProxyManager proxyManager;
    private ChromeBrowser chromeBrowser;
    private EdgeBrowser edgeBrowser;
    private FirefoxBrowser firefoxBrowser;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public BrowserSelector() {
        proxyManager = new ProxyManager();
        browserInput();
    }

    public void browserInput(){
        String[] options = new String[] {"Chrome", "Edge", "Firefox"};
        int response = JOptionPane.showOptionDialog(null, "Message", "Title",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        switch (response) {
            case 0 :
                chromeBrowser = new ChromeBrowser();
            case 1 :
                edgeBrowser = new EdgeBrowser();
            case 2 :
                firefoxBrowser = new FirefoxBrowser();
        }
    }

}