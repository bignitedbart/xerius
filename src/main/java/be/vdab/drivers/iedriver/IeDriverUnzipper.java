package be.vdab.drivers.iedriver;
import be.vdab.utilities.Extractor;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class IeDriverUnzipper {

    private final String SOURCE = "src/test/resources/IEDriverServer_Win32_3.2.0.zip";
    private final String DESTINATION = "src/test/resources/";
    private final String FILENAME = "IEDriverServer.exe";
    private File zip;
    private File binary;
    private Extractor extractor;

    public IeDriverUnzipper() {
        extractor   = new Extractor();
        zip = new File(SOURCE);
        binary = new File(DESTINATION + FILENAME);
    }

    public void unzipIedriver() throws IOException {
        if (!zip.exists()) {
            throw new RuntimeException("Zip-file " + zip + " does not exist!");
        }
        if (binary.exists()) {
            FileUtils.forceDelete(binary);
        }
        extractor.extract(zip, DESTINATION);
    }
}
