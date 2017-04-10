package be.vdab.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static PropertiesLoader ourInstance = new PropertiesLoader();

    private Properties properties;

    public static PropertiesLoader getInstance() {
        return ourInstance;
    }

    private PropertiesLoader() {
        if (null == properties || properties.isEmpty()) {
            loadProperties();
        }
    }

    private void loadProperties() {
        properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Boolean isCorporateProxyEnabled() { return Boolean.parseBoolean(properties.getProperty("corporate.proxy.enabled")); }

    public String getCorporateProxyHost() {
        return properties.getProperty("corporate.proxy.host");
    }

    public int getCorporateProxyPort() {
        return Integer.parseInt(properties.getProperty("corporate.proxy.port"));
    }

    public String[] getBlacklistedExtensions() {
        return properties.getProperty("blacklisted.extensions").split(",");
    }

    public String getGeckoDriverReleaseUrl() { return properties.getProperty("geckodriver.release.url"); }

    public String getChromeDriverReleaseUrl() { return properties.getProperty("chromedriver.release.url"); }

    public String getEdgeDriverReleaseUrl() { return properties.getProperty("edgedriver.release.url");}

    public String getChromeDriverBaseUrl(){ return properties.getProperty("chromedriver.base.url");}

    public String getEdgeDriverBaseUrl(){ return properties.getProperty("edgedriver.base.url");}

    public String getIEDriverBaseUrl(){ return properties.getProperty("internetexplorer.base.url");}

    public String getChromePrefix() { return properties.getProperty("FILENAME_PREFIX_CHROME");}

    public String getChromeSuffix() { return properties.getProperty("FILENAME_SUFFIX_CHROME");}

    public String getFirefoxSuffix() { return properties.getProperty("FILENAME_SUFFIX_FIREFOX");}

    public String getFirefoxPrefix() { return properties.getProperty("FILENAME_PREFIX_FIREFOX");}

    public String getIePrefix(){ return properties.getProperty("FILENAME_PREFIX_IE");}

    public String getIeSuffix(){ return properties.getProperty("FILENAME_SUFFIX_IE");}
}
