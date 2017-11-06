package browser;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

interface Browser {

    void setDriver();

    WebDriver getDriver();

    void killDriver();

    @Rule
    TestRule watcher = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.out.println("**********************************************************************");
            System.out.println("Start test");
            System.out.println("**********************************************************************");
        }

        @Override
        protected void finished(Description description) {
            System.out.println("**********************************************************************");
            System.out.println("Finished test : " + description.getClassName());
            System.out.println("**********************************************************************");
        }
    };

}
