package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.BasePage;
import pages.HomePage;



import java.util.logging.Logger;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homepage;
    Logger log = Logger.getLogger(BasePage.class.getName());

    /**
     * Method to create a new instance of Home Page
     */
    public void setUpStartApp() {
        homepage = new HomePage(driver);
    }

    /**
     * Set up method runs before method setting the browsers and creating a new Home Page in Travelocity´s app
     * @param url
     * @param browserType
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"URL","browserType"})
    public void setUp(@Optional("https://www.travelocity.com/")String url, @Optional("chrome") String browserType){
        log.info("Open browser on Travelocity");

      if(browserType.equalsIgnoreCase("chrome")){
          WebDriverManager.chromedriver().setup();
          driver = new ChromeDriver();
      }else if(browserType.equalsIgnoreCase("firefox")){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
      }
      driver.manage().window().maximize();
      driver.get(" https://www.travelocity.com/");
      setUpStartApp();
    }

    /**
     * Method to close browser after each method run
     */
   @AfterMethod
    public void tearDown(){
        log.info("Close browser");
        driver.quit();
    }
}






