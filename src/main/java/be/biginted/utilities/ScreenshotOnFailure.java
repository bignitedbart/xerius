package be.biginted.utilities;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;


public class ScreenshotOnFailure  implements MethodRule{

    private RemoteWebDriver driver;


    public ScreenshotOnFailure(RemoteWebDriver driver){
        this.driver = driver;
    }

    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                    // exception will be thrown only when a test fails.
                    captureScreenShot();
                    // rethrow to allow the failure to be reported by JUnit
                    throw t;
                }
            }

            public void captureScreenShot() throws IOException {
                File scrFile = driver.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File((System.getProperty("user.home"))+"\\"+ LocalDateTime.now()+".png"));
            }
        };
    }
}



