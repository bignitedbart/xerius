import be.biginted.utilities.ScreenshotOnFailure;
import browser.InternetExplorerBrowser;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IeTest {

    private InternetExplorerBrowser InternetExplorerDriver;
    private RemoteWebDriver driver;

    @Before
    public void setup() throws Exception {
        InternetExplorerDriver = new InternetExplorerBrowser();
        driver = InternetExplorerDriver.getDriver();
    }

    @Test
    public void internetExplorer() {
        driver.get("https://www.ebay.com");
        driver.findElement(By.id("gh-ac")).sendKeys("Batman");
        driver.findElement(By.id("gh-btn")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("Results")));
    }

    @After
    public void teardown() {
        InternetExplorerDriver.killDriver();
    }
    @Rule
    public ScreenshotOnFailure failure = new ScreenshotOnFailure(driver);

}
