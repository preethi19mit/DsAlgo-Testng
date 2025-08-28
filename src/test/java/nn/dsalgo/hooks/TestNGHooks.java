package nn.dsalgo.hooks;

import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.utilities.BaseLogger;
import nn.dsalgo.utilities.ConfigReader;
import nn.dsalgo.utilities.LoggerHelper;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestNGHooks extends BaseLogger {

    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    Properties prop;
    private static final String LOG_FILE_PATH = "target/logs/execution.log";

    @BeforeClass(alwaysRun = true)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setBrowser(@Optional("chrome") String browser) {
        DriverFactory.setBrowser(browser);
        log.info("Running tests on: " + browser + " | Thread ID: " + Thread.currentThread().getId());
    }

    @BeforeMethod(alwaysRun = true)
    public void launchBrowser() {
        String browserName = DriverFactory.getBrowser();
        if (browserName == null || browserName.isEmpty()) {
            browserName = prop.getProperty("browser");
        }
        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browserName);
        log.info("Browser launched: " + browserName + " | Thread: " + Thread.currentThread().getId());
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method method) {
        log.info("Starting test: " + method.getName());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        log.info(">>> After all tests - quitting driver");
        DriverFactory.quitDriver();
    }

}
