package utilities;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            String reportName = "ExtentReport_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/" + reportName);
            reporter.config().setDocumentTitle("JetFlix Automation Report");
            reporter.config().setReportName("JetFlix Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Your Name");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Platform", "Android");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        test = getReportInstance().createTest(testName);
        return test;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}

