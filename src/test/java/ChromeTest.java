import be.biginted.utilities.ScreenshotOnFailure;
import browser.ChromeBrowser;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.SeleniumScreenRecorder;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

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

    @Rule
    public ScreenshotOnFailure failure = new ScreenshotOnFailure(driver);


    @AfterClass
    public static void teardown() throws IOException {
        seleniumScreenRecorder.stopRecording();
        chromeBrowser.killDriver();

    }

}
