package be.vdab.iedriver;

import be.vdab.utilities.DownloadHandler;
import be.vdab.utilities.PropertiesLoader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class IeDriverVersionChecker {

    private final PropertiesLoader LOADER = PropertiesLoader.getInstance();
    private DownloadHandler downloadHandler;

    public IeDriverVersionChecker() throws IOException {
        this.downloadHandler = new DownloadHandler(LOADER.getIEDriverBaseUrl());
    }

    public String getVersion() throws IOException {
        System.out.println(getContentFromResponse());
        return IOUtils.toString(getContentFromResponse(), Charset.defaultCharset()).trim();
    }

    private InputStream getContentFromResponse() throws IOException {
        System.out.println(downloadHandler.getEntity().getContent());
        return downloadHandler.getEntity().getContent();
    }





}
