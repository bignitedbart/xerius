package be.vdab;


import be.vdab.chromedriver.ChromeDriverVersionChecker;
import be.vdab.edgedriver.EdgeDriverVersionChecker;
import be.vdab.geckodriver.GeckoDriverVersionChecker;
import be.vdab.utilities.DownloadHandler;
import be.vdab.utilities.PropertiesLoader;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class DriverDownloader  {

    String FILENAME_PREFIX = "geckodriver-";
    String FILENAME_SUFFIX = "-win32.zip";
    String VERSION;
    String BASE_URL = "https://chromedriver.storage.googleapis.com/";
    String FILENAME = "chromedriver_win32.zip";
    String SAVE_PATH = "src/test/resources/";
    PropertiesLoader propertiesLoader;

    public void downloadBinary(String Browser) throws IOException, URISyntaxException {
        InputStream content;
        File driverZip;
        String browser = Browser.toLowerCase();

        switch (browser) {
            case "chrome":
                BASE_URL = propertiesLoader.getInstance().getChromeDriverBaseUrl();
            DownloadHandler downloadHandler = new DownloadHandler(BASE_URL + new ChromeDriverVersionChecker().getVersion() + "/" + FILENAME);
                content = downloadHandler.getEntity().getContent();
                driverZip  = new File(SAVE_PATH + FILENAME);
                if (driverZip.exists()) {
                    FileUtils.forceDelete(driverZip);
                }
                FileUtils.copyInputStreamToFile(content, new File(SAVE_PATH + FILENAME));
                break;

            case "firefox":
                VERSION = new GeckoDriverVersionChecker().getVersion();
                DownloadHandler downloadHandler1 = new DownloadHandler(getDownloadPath());
                content = downloadHandler1.getEntity().getContent();
                driverZip = new File(SAVE_PATH + getFileName());
                if(driverZip.exists()) {
                    FileUtils.forceDelete(driverZip);
                }
                FileUtils.copyInputStreamToFile(content, new File(SAVE_PATH + getFileName()));
                break;

            case "edge":
                BASE_URL = propertiesLoader.getInstance().getEdgeDriverBaseUrl();
                VERSION = new EdgeDriverVersionChecker().getVersion();
                DownloadHandler downloadHandler2 = new DownloadHandler(getDownloadPathEdge());
                content = downloadHandler2.getEntity().getContent();
                driverZip = new File(SAVE_PATH + "MicrosoftWebDriver.exe");

                if(driverZip.exists()){
                    FileUtils.forceDelete(driverZip);
                }
                FileUtils.copyInputStreamToFile(content, new File(SAVE_PATH + "MicrosoftWebDriver.exe"));
                break;
        }}


    public DriverDownloader() throws IOException {
    }

    private String getDownloadPath() {
        return BASE_URL + VERSION + "/" + getFileName();
    }

    private String getDownloadPathEdge(){
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

    private String getFileName() {
        return FILENAME_PREFIX + VERSION + FILENAME_SUFFIX;
    }
}


