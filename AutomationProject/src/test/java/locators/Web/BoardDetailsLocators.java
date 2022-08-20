package locators.Web;

/**
 * @author Gaurav Kumar
 * Web Locators for Board details page for Trello application
 */
public class BoardDetailsLocators {
	public static final String AddACard_Xpath = ".//h2[text()='To Do']//parent::div//parent::div//span[text()='Add a card']";
	public static final String cardTitleField_Xpath = ".//textarea[contains(@placeholder,'Enter a title for this')]";
	public static final String cardTileValue_xpath = ".//div[contains(@class,'list-cards')]//span[contains(@class,'card-title')]";
}
