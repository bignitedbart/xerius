package be.vdab.geckodriver;

import be.vdab.utilities.DownloadHandler;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class GeckoDriverDownloader {

    private final String BASE_URL = "https://github.com/mozilla/geckodriver/releases/download/";
    private final String FILENAME_PREFIX = "geckodriver-";
    private final String FILENAME_SUFFIX = "-win32.zip";
    private final String SAVE_PATH = "src/test/resources/";
    private final String VERSION;

    public GeckoDriverDownloader() throws IOException {
        VERSION = new GeckoDriverVersionChecker().getVersion();
    }

    public void downloadBinary() throws IOException, URISyntaxException {
        DownloadHandler downloadHandler = new DownloadHandler(getDownloadPath());
        InputStream content = downloadHandler.getEntity().getContent();
        File chromeDriverZip = new File(SAVE_PATH + getFileName());
        if(chromeDriverZip.exists()) {
            FileUtils.forceDelete(chromeDriverZip);
        }
        FileUtils.copyInputStreamToFile(content, new File(SAVE_PATH + getFileName()));
    }

    private String getDownloadPath() {
        return BASE_URL + VERSION + "/" + getFileName();
    }

    private String getFileName() {
        return FILENAME_PREFIX + VERSION + FILENAME_SUFFIX;
    }
}
