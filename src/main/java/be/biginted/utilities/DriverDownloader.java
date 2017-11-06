package be.biginted.utilities;


import be.biginted.drivers.chromedriver.ChromeDriverVersionChecker;
import be.biginted.drivers.edgedriver.EdgeDriverVersionChecker;
import be.biginted.drivers.geckodriver.GeckoDriverVersionChecker;
import be.biginted.drivers.iedriver.IeDriverVersionChecker;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class DriverDownloader {

    String VERSION;
    String BASE_URL;
    String FILENAME;
    String SAVE_PATH = "src/test/resources/";
    PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();


    public void downloadBinary(String Browser) throws IOException, URISyntaxException {
        InputStream content;
        File driverZip;
        String browser = Browser.toLowerCase();

        switch (browser) {
            case "chrome":
                //64 bit download
                if (System.getProperty("os.name").contains("Windows") && System.getProperty("os.arch").contains("64")) {
                    //No 64 bit download available for chromedriver(windows)
                    FILENAME = propertiesLoader.getChromePrefix() + "win32.zip";
                } else if (System.getProperty("os.name").contains("linux") && System.getProperty("os.arch").contains("64")) {
                    FILENAME = propertiesLoader.getChromePrefix() + "linux64.zip";
                } else if (System.getProperty("os.name").contains("mac") && System.getProperty("os.arch").contains("64")) {
                    //No 64 bit download available for chromedriver(mac os)
                    FILENAME = propertiesLoader.getChromePrefix() + "mac32.zip";
                }

                //32 bit download
                if (System.getProperty("os.name").contains("Windows") && System.getProperty("os.arch").contains("32")) {
                    FILENAME = propertiesLoader.getChromePrefix() + "win32.zip";
                } else if (System.getProperty("os.name").contains("linux") && System.getProperty("os.arch").contains("32")) {
                    FILENAME = propertiesLoader.getChromePrefix() + "linux32.zip";
                } else if (System.getProperty("os.name").contains("mac") && System.getProperty("os.arch").contains("32")) {
                    FILENAME = propertiesLoader.getChromePrefix() + "mac32_zip";
                }
                BASE_URL = propertiesLoader.getChromeDriverBaseUrl();
                DownloadHandler downloadHandler1 = new DownloadHandler(BASE_URL + new ChromeDriverVersionChecker().getVersion() + "/" + FILENAME);
                content = downloadHandler1.getEntity().getContent();
                driverZip = new File(SAVE_PATH + FILENAME);
                if (driverZip.exists()) {
                    FileUtils.forceDelete(driverZip);
                }
                FileUtils.copyInputStreamToFile(content, new File(SAVE_PATH + FILENAME));
                break;

            case "firefox":
                //64 bit download
                if (System.getProperty("os.name").contains("Windows") && System.getProperty("os.arch").contains("64")) {
                    FILENAME = propertiesLoader.getFirefoxPrefix() + "win64.zip";
                } else if (System.getProperty("os.name").contains("linux") && System.getProperty("os.arch").contains("64")) {
                    FILENAME = propertiesLoader.getFirefoxPrefix() + "linux64.tar.gz";
                } else if (System.getProperty("os.name").contains("mac") && System.getProperty("os.arch").contains("64")) {
                    FILENAME = propertiesLoader.getFirefoxPrefix() + "macos.tar.gz";
                }

                //32 bit download
                if (System.getProperty("os.name").contains("Windows") && System.getProperty("os.arch").contains("32")) {
                    FILENAME = propertiesLoader.getFirefoxPrefix() + "win32.zip";
                } else if (System.getProperty("os.name").contains("linux") && System.getProperty("os.arch").contains("32")) {
                    FILENAME = propertiesLoader.getFirefoxPrefix() + "linux32.tar.gz";
                } else if (System.getProperty("os.name").contains("mac") && System.getProperty("os.arch").contains("32")) {
                    FILENAME = propertiesLoader.getFirefoxPrefix() + "macos.tar.gz";
                }

                BASE_URL = propertiesLoader.getGeckoDriverBaseUrl();
                VERSION = new GeckoDriverVersionChecker().getVersion();
                FILENAME = propertiesLoader.getFirefoxPrefix() + VERSION + propertiesLoader.getFirefoxSuffix();
                System.out.println(BASE_URL + VERSION + "/" + FILENAME);
                DownloadHandler downloadHandler2 = new DownloadHandler(BASE_URL + VERSION + "/" + FILENAME);

                content = downloadHandler2.getEntity().getContent();
                driverZip = new File(SAVE_PATH + FILENAME);
                if (driverZip.exists()) {
                    FileUtils.forceDelete(driverZip);
                }
                FileUtils.copyInputStreamToFile(content, new File(SAVE_PATH + FILENAME));
                break;

            case "edge":
                BASE_URL = propertiesLoader.getEdgeDriverBaseUrl();
                VERSION = new EdgeDriverVersionChecker().getVersion();
                DownloadHandler downloadHandler3 = new DownloadHandler(getDownloadPathEdge());
                content = downloadHandler3.getEntity().getContent();
                driverZip = new File(SAVE_PATH + "MicrosoftWebDriver.exe");

                if (driverZip.exists()) {
                    FileUtils.forceDelete(driverZip);
                }
                FileUtils.copyInputStreamToFile(content, new File(SAVE_PATH + "MicrosoftWebDriver.exe"));
                break;

            case "internetExplorer":
                BASE_URL = propertiesLoader.getIEDriverBaseUrl();
                VERSION = new IeDriverVersionChecker().getVersion();
                DownloadHandler downloadHandler4 = new DownloadHandler(BASE_URL);
                content = downloadHandler4.getEntity().getContent();
                driverZip = new File(SAVE_PATH + propertiesLoader);

                if (driverZip.exists()) {
                    FileUtils.forceDelete(driverZip);
                }
                FileUtils.copyInputStreamToFile(content, new File(SAVE_PATH + propertiesLoader.getIePrefix() + propertiesLoader.getIeSuffix() + VERSION + ".zip"));
                break;
        }
    }


    public DriverDownloader() throws IOException {
    }


    private String getDownloadPathEdge() {
        Document doc = null;
        try {
            doc = Jsoup.connect(BASE_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements downloadLink = doc
                .select("ul.driver-downloads li.driver-download > a");

        String url = new String(downloadLink.get(0).attr("href"));

        System.out.println(url);
        return url;
    }

}


