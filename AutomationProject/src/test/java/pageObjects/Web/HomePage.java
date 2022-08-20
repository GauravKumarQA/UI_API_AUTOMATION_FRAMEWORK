package pageObjects.Web;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import locators.Web.BoardDetailsLocators;
import locators.Web.HomePageLocators;
import pageObjects.PageObjectInterfaces.IHome;
import utils.UIWrapper;

public class HomePage implements IHome{
	 
	 //Object for UI Selenium wrapper
	 UIWrapper ui = new UIWrapper();
	 
	//Logger
	public static Logger log = Logger.getLogger(HomePage.class.getName());
	
	ExtentTest reporter;
	public HomePage(ExtentTest reporter) {
		this.reporter = reporter;
	}
	
	/** This method will open board details page by clicking on the Board
	 * @param driver WebDriver
	 * @param boardName String
	 * @return boolean
	 */
	public boolean openExistingBoard(WebDriver driver, String boardName) {
		log.info("Opening board " + boardName);
		ui.findElementClick(driver, By.xpath(HomePageLocators.boardName_Xpath));
		if(ui.isElementPresent(driver, By.xpath(BoardDetailsLocators.AddACard_Xpath))) {
			log.info("User is able to open board");
			return true;
		}
		else {
			log.error("User is not able to open board");
			return false;
		}
	}
	
	@Override
	public boolean validateUserIsLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean getAllBoards() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean creatBorad(String boardName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBoard(String boardID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addList(String listName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeList(String listName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCard(String cardName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCard(String cardName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean moveAcard(String from, String to, String cardName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllBoard() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
