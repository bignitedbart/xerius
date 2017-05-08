package be.vdab.drivers.chromedriver;

import be.vdab.utilities.Unzipper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ChromeDriverUnzipper {

    private final String SOURCE = "src/test/resources/chromedriver_win32.zip";
    private final String DESTINATION = "src/test/resources/";
    private final String FILENAME = "chromedriver.exe";
    private File zip;
    private File binary;
    private Unzipper unzipper;

    public ChromeDriverUnzipper() {
        unzipper = new Unzipper();
        zip = new File(SOURCE);
        binary = new File(DESTINATION + FILENAME);
    }

    public void unzipChromedriver() throws IOException {
        if (!zip.exists()) {
            throw new RuntimeException("Zip-file " + SOURCE + " does not exist!");
        }
        if (binary.exists()) {
            FileUtils.forceDelete(binary);
        }
        unzipper.unZipIt(SOURCE, DESTINATION);
    }
}
