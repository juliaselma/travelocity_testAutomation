package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class HomePage extends BasePage{

    /**
     * Constructor method of Home Page
     * @param driver
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "ul#uitk-tabs-button-container > li:nth-of-type(2)")
    private WebElement flightsButton;

    @FindBy(css = "ul#uitk-tabs-button-container>div>li:nth-of-type(1)")
    private WebElement roadTripButton;

    @FindBy(css = "button[data-stid='location-field-leg1-origin-menu-trigger']")
    private WebElement departureButton;

    @FindBy(css = "button[data-stid='location-field-leg1-destination-menu-trigger']")
    private WebElement destinationButton;

    @FindBy(css = "ul[data-stid='location-field-leg1-origin-results'] li:nth-of-type(1)>button")
    private WebElement selectedDepartureCity;

    @FindBy(css = "ul[data-stid='location-field-leg1-destination-results'] li:nth-of-type(1)>button")
    private WebElement selectedDestinationCity;

    @FindBy(css = " div#adaptive-menu>a")
    private WebElement travelersFieldLink;

    @FindBy(css = "div.uitk-flex.uitk-flex-item.uitk-step-input-controls>button:nth-of-type(2)")
    private WebElement increaseAdults;

    @FindBy(css = "button[data-testid='guests-done-button']")
    private WebElement doneSelectingTravelers;

    @FindBy(css = "button#d1-btn")
    private WebElement departingButton;

    @FindBy(css = "button[data-stid='apply-date-picker']")
    private WebElement applyDatePickerButton;

    @FindBy(css = "button[data-testid='submit-button']")
    private WebElement submitButton;

    @FindBy(css = "div.uitk-date-picker-menu-pagination-container button:nth-of-type(2)")
    private WebElement nextMonthButton;

    @FindBy(css = "div.uitk-date-picker-menu-months-container>div:first-child h2")
    private WebElement monthAndYear;

    @FindBy(css = "div.uitk-date-picker-menu-months-container div:first-child button.uitk-new-date-picker-day")
    List<WebElement> days;



    /**
     * Method to click on 'flights' home page option
     */
    public void goToFlights(){ flightsButton.click(); }

    /**
     * Method to click on roadTrip home page option
     */
    public void goToRoadTrip(){
        roadTripButton.click();
    }

    /**
     * Method to enter departure city
     * @param departureCity
     */
    public void enterDepartureCity (String departureCity){
        departureButton.sendKeys(departureCity);
        click(selectedDepartureCity,customWait.NORMAL_WAIT_SECONDS);
    }

    /**
     * Method to enter destination city
     * @param destination
     */
    public void enterDestination(String destination){
        destinationButton.sendKeys(destination);
        click(selectedDestinationCity,customWait.NORMAL_WAIT_SECONDS);
    }

    /**
     * Method to select number of travelers
     */
    public void selectNumberOfTravelers(){
        click(travelersFieldLink,customWait.NORMAL_WAIT_SECONDS);
        click(increaseAdults,customWait.NORMAL_WAIT_SECONDS);
        click(doneSelectingTravelers,customWait.NORMAL_WAIT_SECONDS);
    }

    /**
     * Method to submit search
     * @return new ResultPage
     */
    public ResultsPage submitSearch(){
        click(submitButton,customWait.NORMAL_WAIT_SECONDS);
        return new ResultsPage(driver);
    }

    /**
     * Method to make a full search
     * @param departureCity
     * @param destinationCity
     * @param departure
     * @param arrival
     */
    public void makeYourSearch(String departureCity, String destinationCity,String departure, String arrival){
        goToFlights();
        goToRoadTrip();
        enterDepartureCity(departureCity);
        enterDestination(destinationCity);
        selectNumberOfTravelers();
        selectDates(departure,arrival);
        click(applyDatePickerButton,customWait.NORMAL_WAIT_SECONDS);
    }

    /**
     * Method to select dynamic departure and arrival dates
     * @param departure
     * @param arrival
     */

    public void selectDates(String departure, String arrival){
        click(departingButton,customWait.NORMAL_WAIT_SECONDS);
        while(!monthAndYear.getText().equals(formatMonthAndYear(departure))){
            click(nextMonthButton,customWait.NORMAL_WAIT_SECONDS);
        }
        selectDate(days,formatDate(departure));
        selectDate(days,formatDate(arrival));
    }

    /**
     * Method to format the Month and year of the selected dates
     * @param date
     * @return string with month and year
     */
    public String formatMonthAndYear(String date){//"16 October 2021"
        String day = date.split(" ")[0];
        String month = date.split (" ")[1];
        String year = date.split(" ")[2];
        String monthAndYear = month+" "+year;
        return monthAndYear;
    }

    /**
     * Method to format date of the selected dates
     * @param date
     * @return string with date
     */
    public String formatDate(String date){
        String day = date.split(" ")[0];
        return day;
    }

    /**
     * Method to select the desired date and click it
     * @param daysCollection of available dates to pick
     * @param date selected
     */
    public void selectDate(List<WebElement> daysCollection, String date){
        for(WebElement day : daysCollection){
            String dayText = day.getAttribute("data-day");
            if(dayText.equals(date)){
                System.out.println(day);
                click(day,customWait.NORMAL_WAIT_SECONDS);
           }
        }
    }
}
