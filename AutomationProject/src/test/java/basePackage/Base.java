/*
 * @Author-Gaurav Kumar
 * Automation frame work Base Browser handling class.
 * Configured for Chrome, Firefox, IE, PhantomJS etc as its scalable class.
 * Remote webDriver is also present.
 * 
 * */


package basePackage;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;


/**
 * @author Gaurav Kumar
 * This class contains method to initialize the web browsers
 * Using Webdriver manager for the implementation [Version details can be found in Project POM.xml file]
 * This class is designed in a way that we can scale our scope of bowser automation.
 *
 */
public class Base {
	
	
	//Getting browser name from Constant
	String browserName = Constants.BROWSER;
	
	//WebDriver declaration
	protected WebDriver driver;
	
	//Logger Declaration
	public static Logger log;
	
	// ExtentTest threadLocal to be able to use in parallel testing
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	 /**
	 * Initializing logger
	 */
	@BeforeSuite(alwaysRun = true)
	    public void setupBeforeSuite() {
		 	
	        log = Logger.getLogger(this.getClass().getName());
	        PropertyConfigurator.configure("Log4j.properties");

	        // Getting log level value from Constant
	        log.setLevel(Level.toLevel(Constants.LOG_LEVEL));
	        
	        log.info("*** BeforeSuite: Completed ***");
	        
	    }
	
	
	/**
	 * To create webdriver instance , Browser name is controlled by 
	 * Constant file
	 * @return WebDriver
	 */
	public WebDriver openLocalBowser(){
		
		if(browserName.equalsIgnoreCase("chrome")){
			return chromeInit();
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			return firefoxInit();
		}
		else if(browserName.equalsIgnoreCase("IE")){
			return ieInit();
		}
		else{
			System.out.println("Enter Correct Browser name");
			return null;
		}
	}

	//We can add configuration for IE browser in future
	private WebDriver ieInit() {
		//Supports can be added here
		return null;
	}

	/** FireFox driver initialization
	 * @return boolean
	 */
	private WebDriver firefoxInit() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}

	
	/**
	 * Chrome driver initialization
	 * @return WebDriver
	 */
	private WebDriver chromeInit() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		return driver;
		
	}
	
	
	
	/**
	 * To terminate browser process after completion of test
	 * This is scalable method ,Other browser support can be added
	 */
	@AfterSuite(alwaysRun = true)
    public void terminateActiveWebDriverProcess() {
        log.info("**** AfterSuite : BaseTestTemplate ****");
        log.info("<<< WebDriver process cleanup >>>");
        try {
            if (System.getProperty("os.name").contains("Windows") && Constants.BROWSER.contains("Chrome")) {
                Process process = Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
                process.destroy();
                log.info("All active WebDriver processes terminated !");
            } else {
                log.info("No active WebDriver process found!");
            }
        } catch (Exception e) {
            log.info("<<< Error occured while cleaning up WebDriver process." + e);

        }
    }
	
	
}
