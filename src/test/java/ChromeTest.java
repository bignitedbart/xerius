import be.vdab.utilities.ScreenshotOnFailure;
import browser.ChromeBrowser;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.SeleniumScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class ChromeTest{

    private static RemoteWebDriver driver;
    private static ChromeBrowser chromeBrowser;
    private static SeleniumScreenRecorder seleniumScreenRecorder;


    @BeforeClass
    public static void setup() throws IOException, AWTException, URISyntaxException {
        chromeBrowser = new ChromeBrowser();
        seleniumScreenRecorder = new SeleniumScreenRecorder();
        seleniumScreenRecorder.startRecording();
        driver = chromeBrowser.getDriver();
    }

    @Test
    public void test() {
        driver.get("https://www.ebay.com");
        driver.findElement(By.id("gh-ac")).sendKeys("Batman");
        driver.findElement(By.id("gh-btn")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("Results")));
    }


    @After
    public void teardown() throws IOException {
        seleniumScreenRecorder.stopRecording();
        chromeBrowser.killDriver();

    }

    @Rule
    public ScreenshotOnFailure failure = new ScreenshotOnFailure(driver);
}
