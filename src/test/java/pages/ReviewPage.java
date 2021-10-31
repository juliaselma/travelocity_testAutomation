package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class ReviewPage extends BasePage {

    /**
     * constructor method
     * @param driver
     */
    public ReviewPage(WebDriver driver) {
        super(driver);
    }

    ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());

    @FindBy(css = "a[data-test-id='forcedChoiceNoThanks']")
    private WebElement popUpNoThanksButton;

    @FindBy(css = "div[data-test-id='journey-duration']")
    List<WebElement> durationResults;
    List<String> durationText = new ArrayList<>();

    @FindBy(css = "td span")
    private WebElement tripTotalPrice;

    @FindBy(css = "div[data-test-id='flight-review-0'] h2")
    private WebElement departureAndDestination;

    @FindBy(css = "h2.uitk-messaging-card-section-header")
    private WebElement banner;

    @FindBy(css = "div[data-test-id='flight-review-0'] div[data-test-id='fare-summary'] h3")
    private WebElement fare;

    @FindBy(css = " button[data-test-id=\"goto-checkout-button\"]")
    private WebElement checkOutButton;


    /**
     * Method to switch tab
     */
    public void swithToActiveTab(){
        driver.switchTo().window(tabs2.get(1));
    }

    /**
     * Method to check the total price is present in the review
     * @return boolean
     */
    public boolean isTripTotalPricePresent(){
        swithToActiveTab();
        customWait.waitWebElementVisibility(driver,banner,customWait.NORMAL_WAIT_SECONDS);
        return tripTotalPrice.isDisplayed();
    }

    /**
     * Method to check the departure and destination are present in the review page
     * @return boolean
     */
    public boolean areDepartureAndDestinationPresent(){
      // customWait.waitWebElementVisibility(driver, banner);
        return departureAndDestination.isDisplayed();
    }

    /**
     * Method to check 'fare: economy' in the review page
     * @return boolean
     */
    public boolean isFareSelectionEconomy(){
        return (fare.getText().equals("Fare: Economy"));
    }

    /**
     * Method to go to checkout page
     * @return new Checkout page
     */
    public CheckOutPage pressCheckOutButton(){
        swithToActiveTab();
        customWait.waitWebElementVisibility(driver, banner,customWait.NORMAL_WAIT_SECONDS);
        click(checkOutButton,customWait.NORMAL_WAIT_SECONDS);
        return new CheckOutPage(driver);
    }
}
