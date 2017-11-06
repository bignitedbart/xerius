package be.biginted.drivers.chromedriver;

import be.biginted.utilities.DownloadHandler;
import be.biginted.utilities.PropertiesLoader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ChromeDriverVersionChecker  {

    private final PropertiesLoader LOADER = PropertiesLoader.getInstance();
    private DownloadHandler downloadHandler;

    public ChromeDriverVersionChecker() throws IOException {
        this.downloadHandler = new DownloadHandler(LOADER.getChromeDriverReleaseUrl());
    }

    public String getVersion() throws IOException {
        System.out.println(getContentFromResponse());
        return IOUtils.toString(getContentFromResponse(), Charset.defaultCharset()).trim();
    }

    private InputStream getContentFromResponse() throws IOException {
        return downloadHandler.getEntity().getContent();
    }
}
