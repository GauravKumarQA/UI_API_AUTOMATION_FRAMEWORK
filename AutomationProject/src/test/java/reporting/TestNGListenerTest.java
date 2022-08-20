package reporting;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import basePackage.Base;

public class TestNGListenerTest extends Base implements ITestListener {

    // Initialize Log4j logs
    public static Logger log = Logger.getLogger(TestNGListenerTest.class.getName());
  
    // This will be executed before Any test, but once
    public void onStart(ITestContext context) {
    	log.info("===========================================================");
        log.info("Welocome........");
        log.info("===========================================================");
    }

    // This will be executed before each Test start
    public void onTestStart(ITestResult result) {
    	log.info("===========================================================");
        log.info("Test Case Execution Started,Test Name is:\t" + result.getName());
        log.info("===========================================================");
      }

    // This will be executed for each Test if only Test is PASSED
    public void onTestSuccess(ITestResult result) {
        if (result.getParameters().length > 0) {
            log.info("===========================================================");
            log.info("Test Case Executed Sucesfully,Test Name is:\t" + result.getName());
            log.info("===========================================================");
        } else {
            log.info("===========================================================");
            log.info("Test Case Executed Sucesfully,Test Name is:\t" + result.getName());
            log.info("===========================================================");
        }

    }

    // This will be executed for each Test if only Test is FAILED
    public void onTestFailure(ITestResult result) {
		  log.info("===========================================================");
		  log.info("Test Case Execution Got Failed,Test Name is:\t" +
		  result.getName());
		  log.info("===========================================================");		 
    }

    public void onTestSkipped(ITestResult result) {
        log.info("===========================================================");
        log.info("Test Case is Skipped,Test Name is:\t" + result.getName());
        log.info("===========================================================");
    }

    public void onFinish(ITestContext context) {
        log.info("========== All Testcase execution ended ==========");
        log.info("========== View reports for details of execution :" + ExtentReportManager.fullReportPath +" ==========");
      }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        // TODO Auto-generated method stub
    }


}
