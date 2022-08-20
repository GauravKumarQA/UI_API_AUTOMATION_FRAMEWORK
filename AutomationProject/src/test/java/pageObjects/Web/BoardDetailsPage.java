package pageObjects.Web;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import locators.Web.BoardDetailsLocators;
import reporting.ExtentReportManager;
import utils.UIWrapper;

public class BoardDetailsPage {
	
	UIWrapper ui = new UIWrapper();
	ExtentTest reporter;
	ExtentReports report;
	ExtentReportManager rp= new ExtentReportManager();
	
	public BoardDetailsPage(ExtentTest reporter){
		this.reporter = reporter;
		report = ExtentReportManager.getExtentReports();
	}
	//Logger
	public static Logger log = Logger.getLogger(BoardDetailsPage.class.getName());
	
	/**
	 * To create a card on Board details page
	 * @param driver WebDriver
	 * @param cardName String
	 * @return boolean
	 */
	public boolean addACard(WebDriver driver, String cardName) {
		log.info("Adding card on board : " + cardName);
		ui.findElementClick(driver, By.xpath(BoardDetailsLocators.AddACard_Xpath));
		ui.findElementSendKey(driver, By.xpath(BoardDetailsLocators.cardTitleField_Xpath), cardName);
		ui.findElementSendKey(driver, By.xpath(BoardDetailsLocators.cardTitleField_Xpath), Keys.ENTER);
		driver.navigate().refresh();
		if(ui.getText(driver, By.xpath(BoardDetailsLocators.cardTileValue_xpath)).contains(cardName)) {
			log.info("User is able to add card on board");
			return true;
		}else {
			log.error("Not able to add card on board");
			return false;
		}
	}
	
	
	public boolean moveACard(WebDriver driver, String cardName) {
		return false;
	}
}
