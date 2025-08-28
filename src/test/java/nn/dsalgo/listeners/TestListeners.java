package nn.dsalgo.listeners;

import io.qameta.allure.Attachment;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.utilities.LoggerHelper;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestListeners implements ITestListener {
    private Logger log = LoggerHelper.getLogger(TestListeners.class);
    private static final String LOG_FILE_PATH = "target/logs/execution.log";

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: " + result.getName());

        WebDriver driver = DriverFactory.getDriver(); // ThreadLocal driver

        if (driver != null) {
            try {
                // Save screenshot locally
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destDir = new File("target/screenshots");
                if (!destDir.exists()) destDir.mkdirs();

                String fileName = result.getName().replaceAll("[^a-zA-Z0-9_-]", "_") + ".png";
                File destFile = new File(destDir, fileName);
                Files.copy(srcFile.toPath(), destFile.toPath());
                log.info("✅ Screenshot saved at: " + destFile.getAbsolutePath());

                // Attach screenshot to Allure
                attachScreenshotToAllure(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

                // Attach Log file to Allure (if exists)
                if (Files.exists(Paths.get(LOG_FILE_PATH))) {
                    attachLogToAllure(Files.readAllBytes(Paths.get(LOG_FILE_PATH)));
                    log.info("📄 Log file attached to Allure report: " + LOG_FILE_PATH);
                } else {
                    log.warn("⚠ Log file not found: " + LOG_FILE_PATH);
                }

            } catch (IOException e) {
                log.error("Error capturing screenshot or attaching log", e);
            }
        }
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] attachScreenshotToAllure(byte[] screenshot) {
        return screenshot;
    }

    @Attachment(value = "Execution Log", type = "text/plain")
    public byte[] attachLogToAllure(byte[] logFile) {
        return logFile;
    }

    // Optional overrides for other events
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
