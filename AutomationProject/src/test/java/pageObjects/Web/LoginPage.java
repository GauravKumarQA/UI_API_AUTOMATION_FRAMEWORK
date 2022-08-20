package pageObjects.Web;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import locators.Web.HomePageLocators;
import locators.Web.LoginPageLocators;
import pageObjects.PageObjectInterfaces.ILogin;
import reporting.TestNGListenerTest;
import utils.Constants;
import utils.UIWrapper;

public class LoginPage implements ILogin{
	
	//Logger
	public static Logger log = Logger.getLogger(LoginPage.class.getName());
	
	//Object for UI Selenium wrapper
	UIWrapper ui = new UIWrapper();
	
	ExtentTest reporter;
	public LoginPage(ExtentTest reporter) {
		this.reporter = reporter;
	}
	
	/**Login to Trello application
	 * Credentials are defined in the constant file
	 * @param driver WebDriver
	 * @return boolean
	 */
	public boolean loginToTrello(WebDriver driver) {
		log.info("Login to trello app.");
		log.info("Entering username and password.");
		ui.findElementSendKey(driver, By.id(LoginPageLocators.input_ID), Constants.TRELLO_USERNAME);
		ui.findElementSendKey(driver, By.id(LoginPageLocators.input_ID), Keys.ENTER);
		ui.findElementClick(driver, By.id(LoginPageLocators.loginWithAtlassianButton_ID));
		ui.waitElementUntilDisplayed(driver, By.id(LoginPageLocators.loginSubmit_ID), Constants.DEFAULT_WAIT_TIME);
		ui.findElementSendKey(driver, By.id(LoginPageLocators.password_ID), Constants.TRELLO_PASSWORD);
		ui.findElementClick(driver, By.id(LoginPageLocators.loginSubmit_ID));
		if(ui.isElementPresent(driver, By.xpath(HomePageLocators.boardName_Xpath)) == true ) {
			log.info("Login succefully.");
			return true; 
		}
		else{
			log.error("Login failed.");
			return false ;
		}
	}

	
	public boolean navigateToTrello(WebDriver driver) {
		driver.get(Constants.URL);
		return true;
	}

}
