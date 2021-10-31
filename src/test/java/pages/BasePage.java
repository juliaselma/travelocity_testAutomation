package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class BasePage {

    protected utils.CustomWait customWait = new utils.CustomWait();
    protected WebDriver driver;

    /**
     * constructor method of the Base Page with page Factory
     * @param driver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to click a web element after waiting it is clickeable
     * @param element to be clicked
     */
    public void click(WebElement element,long waitTime) {
        customWait.waitWebElementToBeClickable(driver,element,waitTime);
        element.click();
    }

    /**
     * Method to iterate a List of elements and adds the its texts to an arrayList of Strings
     */
    public void iterateOptions (List<WebElement> options, List<String>optionNames){
        for(WebElement option : options){
            String optionName = option.getText();
            optionNames.add(optionName);
        }
    }
}
