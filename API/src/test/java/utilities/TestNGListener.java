package utilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.*;

public class TestNGListener implements ITestListener {
   private static ExtentReports extent = ExtentReportManager.getReportInstance();
   private static ExtentTest test;

   public TestNGListener(){}
   @Override
   public void onTestStart(ITestResult result) {
       test = ExtentReportManager.createTest(result.getMethod().getMethodName());
   }
   @Override
   public void onTestSuccess(ITestResult result) {
       test.pass("Test Passed: " + result.getMethod().getMethodName());
   }

   @Override
   public void onTestFailure(ITestResult result) {
       test.fail("Test Failed: " + result.getThrowable().getMessage());
   }

   @Override
   public void onTestSkipped(ITestResult result) {
       test.skip("Test Skipped: " + result.getMethod().getMethodName());
   }

   @Override
   public void onFinish(ITestContext context) {
       ExtentReportManager.flushReport();
   }
}



