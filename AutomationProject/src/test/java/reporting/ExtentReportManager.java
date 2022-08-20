package reporting;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import basePackage.Base;
import utils.Constants;


public class ExtentReportManager extends Base {

    // Instantiate ExtentReports class
    public static ExtentReports extentReport;

    public ExtentHtmlReporter htmlReporter;

    //-------Report info------
    //Getting below info from config.properties
    public String ExtentReportOption = "";
    public String projectName = "";

    //Creating current time stamp for report name
    public String date = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());

    //Getting below report info from Environment value(passes as Env param during loal run or param from Jenkins)
    Map<String, String> env = System.getenv();
    public String AppBuildVersion = Constants.BUILD_VERSION;
    public String reportName = Constants.REPORT_NAME;
    public static String fullReportPath;

    //Creating ExtentReport instance
    public ExtentReports createInstance() {
        
    	 	//******** HTML Report Configuration  *******

            // location of the extent report - Local HTML File
            String reportPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReport";
            String reportFileName = File.separator + "AutomationReport_" + date + ".html";
            fullReportPath = reportPath + reportFileName;

            Path path = Paths.get(reportPath);
            if (Files.exists(path)) {
                log.info("=== REPORT PATH : " + fullReportPath);
            } else {
                File directory = new File(reportPath);
                directory.mkdir();
                log.info("=== REPORT PATH : " + fullReportPath);
            }
            File report = new File(reportPath);
            reportDirectoryCleanUp(report, 10);
            htmlReporter = new ExtentHtmlReporter(fullReportPath);

            // Title of the report
            htmlReporter.config().setDocumentTitle(Constants.PROJECT_NAME);

            // Name of the report
            //Checking if AppBuildVersion is not passed in env variable , will set to default value from config.properties
            if (reportName == null) {
                reportName = Constants.REPORT_NAME;
            }
            htmlReporter.config().setReportName("V" + AppBuildVersion + "_" + reportName);
            htmlReporter.config().setTheme(Theme.STANDARD);

            extentReport = new ExtentReports();
            extentReport.attachReporter(htmlReporter);
            
            log.info("=== HTML EXTENT REPORT ENABLED: LOCAL === ");
        

        // General information related to application
        extentReport.setSystemInfo("Environment", Constants.ENVIROMENT);
        extentReport.setSystemInfo("Browser", Constants.BROWSER);

        return extentReport;
    }
    
    public static ExtentReports getExtentReports() {
    	return extentReport;
    }

    //This method will clear the output directory once limit reached
    public static void reportDirectoryCleanUp(File reportDir, int numOfReports) {
        if (reportDir.isDirectory()) {
            List<String> reports = Arrays.asList(reportDir.list());
            System.out.println("reports.size() :" + reports.size());
            if (reports.size() > numOfReports) {

                for (int i = 0; i < reports.size(); i++) {

                    List<String> reports1 = Arrays.asList(reportDir.list());

                    if (reports1.size() > numOfReports) {
                        Collections.sort(reports);
                        File oldreport = new File(reportDir, reports.get(i));
                        if (oldreport.delete()) {
                            System.out.println("Deleted an old report - " + reports.get(i));
                        }
                    } else
                        break;
                }
            }
        }
    }
    
    public void passLog(String testStep,ExtentReports report,ExtentTest reporter)
	{
		reporter.log(Status.PASS, "Step: "+testStep );
		report.flush();
	}
    
    //Log for fail test cases
    public void fail(String testStepName,ExtentReports report,ExtentTest logger,WebDriver driver) throws Exception {
  	  if(!(driver == null)) {
 
  	     	String screenshot_path = takeScreenshotReturnPath(driver);
    	
    			try {
    				logger.fail("Failed test Step: "+testStepName,MediaEntityBuilder.createScreenCaptureFromPath(screenshot_path).build());
    				report.flush();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
  	  }
  	  else {
  		logger.fail("Failed test Step: "+testStepName);
		report.flush();
  	  }
    	}
    
    public void fail(String testStepName,ExtentReports report,ExtentTest logger) {
    	 logger.fail("Failed test Step: "+testStepName);
  		 report.flush();
      }

    //Take screenshots
    public static String takeScreenshotReturnPath(WebDriver driver) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH_mm_ss");
		String formatedDateAndTime = now.format(format);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\Screenshots\\" + formatedDateAndTime.toString() + ".png"));
		return System.getProperty("user.dir")+"\\Screenshots\\" + formatedDateAndTime.toString()+".png";
	
    }
}
