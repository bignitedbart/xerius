package be.biginted.drivers.geckodriver;

import be.biginted.utilities.Extractor;
import be.biginted.utilities.PropertiesLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class GeckoDriverUnzipper {

    PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();

    private final String PATH = "src/test/resources/";
    private final String VERSION;
    private Extractor extractor;
    private File zip;
    private File binary;

    public GeckoDriverUnzipper() throws IOException {
        extractor = new Extractor();
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
        extractor.extract(zip, getDestinationFilePath());
    }

    private String getSourceFilePath() {
        return PATH + getZipFilename();
    }

    private String getDestinationFilePath() {
        return PATH + getBinaryFileName();
    }

    private String getZipFilename() {
        return  propertiesLoader.getFirefoxPrefix() + VERSION + propertiesLoader.getFirefoxSuffix();
    }

    private String getBinaryFileName() {
        return propertiesLoader.getFirefoxPrefix().replace("-","")+".exe";
    }
}
