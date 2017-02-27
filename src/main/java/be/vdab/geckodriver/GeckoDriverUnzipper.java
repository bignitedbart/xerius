package be.vdab.geckodriver;

import be.vdab.utilities.Unzipper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class GeckoDriverUnzipper {

    private final String PATH = "src/test/resources/";
    private final String FILENAME_PREFIX = "geckodriver-";
    private final String FILENAME_SUFFIX = "-win32";
    private final String VERSION;
    private Unzipper unzipper;
    private File zip;
    private File binary;

    public GeckoDriverUnzipper() throws IOException {
        unzipper = new Unzipper();
        VERSION = new GeckoDriverVersionChecker().getVersion();
        zip = new File(getSourceFilePath());
        binary = new File(getDestinationFilePath());
    }

    public void unzipGeckodriver() throws IOException {
        if (!zip.exists()) {
            throw new RuntimeException("Zip-file " + getSourceFilePath() + " does not exist!");
        }
        if (binary.exists()) {
            FileUtils.forceDelete(binary);
        }
        unzipper.unZipIt(getSourceFilePath(), getDestinationFilePath());
    }

    private String getSourceFilePath() {
        return PATH + getZipFilename();
    }

    private String getDestinationFilePath() {
        return PATH + getBinaryFileName();
    }

    private String getZipFilename() {
        return FILENAME_PREFIX + VERSION + FILENAME_SUFFIX + ".zip";
    }

    private String getBinaryFileName() {
        return FILENAME_PREFIX + VERSION + FILENAME_SUFFIX + ".exe";
    }
}
