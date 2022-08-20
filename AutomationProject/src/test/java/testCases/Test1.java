package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import basePackage.Base;
import pageObjects.API.Home;
import pageObjects.Web.BoardDetailsPage;
import pageObjects.Web.HomePage;
import pageObjects.Web.LoginPage;
import reporting.ExtentReportManager;
import reporting.TestNGListenerTest;
import utils.Constants;


@Listeners(TestNGListenerTest.class)
public class Test1 extends Base{

 //initialization
  WebDriver driver;
  Base base = new Base();
  
  
  
  //Extent report references
  ExtentReportManager rp;
  ExtentReports report;
  ExtentTest reporter;
  
  //PageObject References
  BoardDetailsPage trelloBoardDetail;
  Home home;
  LoginPage login;
  HomePage trelloHome;
  
  // Variables Defined
  String testCaseName = "Trello test";
  String testStep;
 
 

  @BeforeClass
  // open the browser
  public void preCondition() {
	 //Extent report object initialization
	 rp = new ExtentReportManager();
	 //Creating Extent instance
	 report = rp.createInstance(); 

	 //Creating reporter and creating required object with reporter
	 reporter = report.createTest(testCaseName);
	 trelloBoardDetail = new BoardDetailsPage(reporter);
	 home = new Home(reporter);
	 login = new LoginPage(reporter);
	 trelloHome = new HomePage(reporter);
	 
	  testStep="Precondition: Deleting all boards from trello instance via API";
	  Assert.assertEquals(home.deleteAllBoard(),Constants.SUCCESS ,  testStep );
	  rp.passLog(testStep, report, reporter); 
      
  }
  
  @Test
  public void UnzerAPI_UI_Automation_Example() throws Exception {
  
  try {
	  
	testStep = "Creating a board with name: TestingBoard via API"; 
	home.creatBorad("TestingBoard");
	Assert.assertEquals(home.creatBorad("TestingBoard"),Constants.SUCCESS ,testStep);
	rp.passLog(testStep, report, reporter); 
	
	testStep="Opening Browser";
    driver = base.openLocalBowser();
    rp.passLog(testStep, report, reporter); 
    
	testStep = "Navigating to trello app from UI";
	Assert.assertEquals(login.navigateToTrello(driver),Constants.SUCCESS, testStep);
	rp.passLog(testStep, report, reporter); 
	
	testStep = "Login to trello app from UI";
	Assert.assertEquals(login.loginToTrello(driver), Constants.SUCCESS, testStep);
	rp.passLog(testStep, report, reporter); 
	
	testStep = "Opening a trello board from UI";
	Assert.assertEquals(trelloHome.openExistingBoard(driver, "TestingBoard"), Constants.SUCCESS, testStep);
	rp.passLog(testStep, report, reporter); 
	
	testStep = "Adding a card to trello board from UI";
	Assert.assertEquals(trelloBoardDetail.addACard(driver, "TestCard"),Constants.SUCCESS, testStep);
	rp.passLog(testStep, report, reporter); 
	
    
} catch (Exception e) {
   rp.fail(testStep, report, reporter,driver); 
   e.printStackTrace();
   Assert.fail(e.getLocalizedMessage());
}
}

@AfterClass
// close the browser
public void closeBrowser() {
testStep = "Closing the browser";
driver.quit();
}

}
