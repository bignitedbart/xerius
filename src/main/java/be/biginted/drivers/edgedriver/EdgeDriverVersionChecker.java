package be.biginted.drivers.edgedriver;

import be.biginted.utilities.PropertiesLoader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class EdgeDriverVersionChecker {

    private final PropertiesLoader LOADER = PropertiesLoader.getInstance();


    public String getVersion() throws IOException {
        String edgeDriverUrl = LOADER.getEdgeDriverReleaseUrl();
        Document doc = Jsoup.connect(edgeDriverUrl).get();
        Elements versionParagraph = doc.select(
                "ul.driver-downloads li.driver-download p.driver-download__meta");
        String[] latestVersion1 = versionParagraph.get(0).text().split(" ");
        String latestVersion = latestVersion1[0] +" " +latestVersion1[1];
        return latestVersion;

    }

}
