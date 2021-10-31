package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage extends BasePage {
    /**
     * Constructor method of Results Page
     * @param driver
     */
    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.uitk-flex-item select[data-test-id='sortDropdown']")
    private WebElement box;

    @FindBy(css = "div.uitk-flex-item select[data-test-id='sortDropdown']>option")
    List<WebElement> options;
    List<String> optionNames = new ArrayList<>();

    @FindBy(css = "div[data-test-id='arrival-departure']")
    List<WebElement> results;
    List<String> departureArrivalText = new ArrayList<>();

    @FindBy(css = "div[data-test-id='price-column']")
    List<WebElement> priceResults;
    List<String> priceText = new ArrayList<>();

    @FindBy(css = "div[data-stid='airline-info']")
    List<WebElement> airlinesResults;
    List<String> airlinesText = new ArrayList<>();

    @FindBy(css = "div[data-test-id='journey-duration']")
    List<WebElement>durationResults;
    List<String> durationText = new ArrayList<>();

    @FindBy(css = "div.uitk-flex-item select[data-test-id='sortDropdown'] option:nth-of-type(3)")
    private WebElement sortShortest;

    @FindBy(css = "li:nth-of-type(1) button")
    private WebElement firstFlightSelectedResult;

    @FindBy(css = "div[data-test-id='flight-summary'] h3>span")
    private WebElement flightTimeSide;

    @FindBy(css = "li:nth-of-type(1) div[data-test-id='arrival-time']>span")
    private WebElement firstFlightTimePrincipalPage;

    @FindBy(css = "button[data-test-id='select-button']")
    private WebElement continueButton;

    @FindBy(css = "li[data-test-id='offer-listing']:nth-of-type(3) button")
    private WebElement secondFlightSelectedResult;

    @FindBy(css = "li:nth-of-type(3) div[data-test-id='arrival-time']>span")
    private WebElement secondFlightTimePrincipalPage;

    @FindBy(css = "a[data-test-id='forcedChoiceNoThanks']")
    private WebElement popUpNoThanksButton;


    /**
     * Method that checks the number of comboBox elements available
     * @return the size of the list containing the Names of the elements
     */
    public int checkNumberOfBoxElements(){
        click(box,customWait.NORMAL_WAIT_SECONDS);
        iterateOptions(options,optionNames);
        return optionNames.size();
    }

    /**
     * Method that checks the names of the comboBox elements available
     * @return the list of names displayed in de comboBox parsed to String
     */
    public String checkNameOfBoxElements(){
        click(box,customWait.NORMAL_WAIT_SECONDS);
        iterateOptions(options,optionNames);
        return optionNames.toString();
    }

    /**
     * Method to control the departure and arrival text is present on every search result
     * @return boolean
     */
    public boolean checkDepartureArrivalInResults(){
      return isTextPresent(results,departureArrivalText);
    }

    /**
     * Method to control the airline text is present on every search result
     * @return boolean
     */
    public boolean checkAirlineInResults(){
        return isTextPresent(airlinesResults,airlinesText);
    }

    /**
     * Method to control the price text is present on every search result
     * @return boolean
     */
    public boolean checkPriceInResults(){
        return isTextPresent(priceResults,priceText);
    }

    /**
     * Method to control the flight duration text is present on every search result
     * @return boolean
     */
    public boolean checkDurationInResults(){
        return isTextPresent(durationResults,durationText);
    }

    /**
     * Method that iterates de flights results checking they are correctly sorted
     * @return boolean
     */
    public boolean iterateAndSort(){
        for(WebElement option : durationResults){
            String optionName = option.getText();
            durationText.add(optionName);
        }
        for(int i =0; i<durationText.size()-1;i++){
            String durationFirstText = durationText.get(i);
            String durationTextNext = durationText.get(i+1);
            if(formatHour(durationFirstText) > formatHour(durationTextNext)){
                return false;
            }else if (formatHour(durationFirstText) == formatHour(durationTextNext)){
                if (formatMinutes(durationFirstText) > formatMinutes(durationTextNext)) {
                    return false;
                }
            }
        }return true;
    }

    /**
     * Method to format the flight duration and obtain the hours to compare
     * @param duration flight duration string
     * @return int hour
     */

    public int formatHour(String duration){
        String hourWithH = duration.split(" ")[0];
        String hour = hourWithH.replaceAll("\\D+","");
        return Integer.parseInt(hour);
    }

    /**
     * Method to format the flight duration and obtain the minutes to compare
     * @param duration flight duration string
     * @return int minutes
     */

    public int formatMinutes(String duration){
        String minutesWithM = duration.split(" ")[1];
        String minutes = minutesWithM.replaceAll("\\D+","");
        return Integer.parseInt(minutes);
    }

    /**
     * Method that selects 'sort shortest' and checks it is correctly sorted
     * @return boolean
     */
    public boolean sortByDuration(){
        click(sortShortest,customWait.NORMAL_WAIT_SECONDS);
        customWait.waitWebElementToBeClickable(driver,firstFlightSelectedResult,customWait.LARGE_WAIT_SECONDS);
        return iterateAndSort();
    }

    /**
     * Helper Method to check if the collection of results has the same size of the list of texts to be searched
     * @param collection is results obtained from a research
     * @param texts is the list of the texts found
     * @return boolean
     */
   public boolean isTextPresent(List<WebElement>collection,List<String>texts){
       for(WebElement result : collection){
           String resultText = result.getText();
           texts.add(resultText);
           if(!(texts.size()== collection.size())){
               return false;
           }
       }return true;
   }

    /**
     * Method that compares in the first flight selected the estimated departure time is the same in the sideBar and in the principal page
     * @return boolean
     */
   public boolean checkFirstResultTexts(){
      return compareTexts(firstFlightSelectedResult, firstFlightTimePrincipalPage, flightTimeSide);
   }

    /**
     * Method to select the desired option for the first flight
     */
   public void selectFirstFlight(){
        click(firstFlightSelectedResult,customWait.NORMAL_WAIT_SECONDS);
        clickContinueButton();
   }

    /**
     * Method that compares in the second flight selected the estimated departure time is the same in the sideBar and in the principal page
     * @return boolean
     * sometimes it needs to be run with wait to pass
     */
    public boolean checkSecondResultTexts(){
        //customWait.waitWebElementInvisibility(driver,continueButton);
        return compareTexts(secondFlightSelectedResult, secondFlightTimePrincipalPage, flightTimeSide);
    }

    /**
     * Method to click the continue button after selecting a flight
     */
    public void clickContinueButton(){
        click(continueButton,customWait.NORMAL_WAIT_SECONDS);
   }

    /**
     * Helper method tha compares the texts in the principal page and the sidebar
     * @param selectedElement is flight selected
     * @param principalPageElement text in the principal page
     * @param sideElement text in the sidebar
     * @return boolean
     */
   public boolean compareTexts(WebElement selectedElement, WebElement principalPageElement, WebElement sideElement){
        click(selectedElement,customWait.NORMAL_WAIT_SECONDS);
        return(principalPageElement.getText().equals(sideElement.getText()));
   }

    /**
     * Method that rejects the "search for hotels" pop up
     * @return new Review Page
     */
   public ReviewPage closePopUp(){
        click(popUpNoThanksButton,customWait.NORMAL_WAIT_SECONDS);
        return new ReviewPage(driver);
   }

    /**
     * Method to select the desired option for the second flight
     */
   public void selectSecondFlight(){
       customWait.waitWebElementVisibility(driver,secondFlightSelectedResult,customWait.LARGE_WAIT_SECONDS);
       click(secondFlightSelectedResult,customWait.NORMAL_WAIT_SECONDS);
       clickContinueButton();
   }

    /**
     * Method to select the desired flights for the trip
     * @return new review trip page
     */
   public ReviewPage goToReviewPage(){
       selectFirstFlight();
       selectSecondFlight();
       return closePopUp();
   }
}




