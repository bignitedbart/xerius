package utilities;

import be.biginted.utilities.PropertiesLoader;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.InetSocketAddress;

public class ProxyManager {

    private BrowserMobProxy browserMobProxy;
    private Proxy seleniumProxy;
    private DesiredCapabilities desiredCapabilities;
    private PropertiesLoader loader;

    public ProxyManager() {
        browserMobProxy = new BrowserMobProxyServer();
        desiredCapabilities = new DesiredCapabilities();
        loader = PropertiesLoader.getInstance();

        if (loader.isCorporateProxyEnabled()) {
            setCorporateProxy(browserMobProxy);
        }

        addHeader(browserMobProxy,"","");
        startBrowserMobProxy();
        seleniumProxy = createSeleniumProxy(browserMobProxy);
        setProxyCapability(desiredCapabilities, seleniumProxy);
    }

    private void setCorporateProxy(BrowserMobProxy browserMobProxy) {
        InetSocketAddress corporateProxyAddress = new InetSocketAddress(loader.getCorporateProxyHost(), loader.getCorporateProxyPort());
        browserMobProxy.setChainedProxy(corporateProxyAddress);
    }

    private BrowserMobProxy startBrowserMobProxy() {
        filterRequestsWithExtensions(loader.getBlacklistedExtensions());
        browserMobProxy.start();
        return browserMobProxy;
    }

    private Proxy createSeleniumProxy(BrowserMobProxy browserMobProxy) {
        if (null == browserMobProxy) {
            startBrowserMobProxy();
        } else {
            seleniumProxy = ClientUtil.createSeleniumProxy(browserMobProxy);
        }
        return seleniumProxy;
    }

    private DesiredCapabilities setProxyCapability(DesiredCapabilities capabilities, Proxy seleniumProxy) {
        if (null == capabilities) {
            throw new RuntimeException("Capabilities have not been set yet!");
        }
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        return capabilities;
    }


    public DesiredCapabilities getDesiredCapabilities() {
        return desiredCapabilities;
    }

    private void filterRequestsWithExtensions(String... extensions) {
        for (String extension : extensions) {
            browserMobProxy.blacklistRequests("^http.*" + extension + "$", 200);
        }
    }

    private void addHeader(BrowserMobProxy proxyInstance, String key, String value){
        proxyInstance.addRequestFilter((request, contents, messageInfo) -> {
            request.headers().set(key, value);
            return null;
        });
    }
}
