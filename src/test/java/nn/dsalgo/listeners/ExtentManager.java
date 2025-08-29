package nn.dsalgo.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = "target/reports/extent/ExtentReport.html";

            // Ensure directories exist
            File reportDir = new File(reportPath).getParentFile();
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("DSAlgo Automation Test Report");
            reporter.config().setDocumentTitle("DSAlgo Test Execution Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Automation Architects");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
