package tests;

import org.testng.annotations.Test;
import pages.ResultsPage;
import utils.dataproviders.SearchDataProvider;

import static org.testng.Assert.assertTrue;

public class SecondFlightResultTests extends BaseTests {
    /**
     * Method testing results of the second flight
     * @param departure
     * @param destination
     * @param departureDate
     * @param returnDate
     */
    @Test(dataProvider = "TestData",dataProviderClass = SearchDataProvider.class)
    public void compareSideBarWithResults(String departure, String destination,String departureDate,String returnDate){
        log.info("Making a new search");
        homepage.makeYourSearch(departure, destination,departureDate,returnDate);
        ResultsPage resultsPage =homepage.submitSearch();
        log.info("Accepting first flight option");
        resultsPage.selectFirstFlight();

        log.info("Testing results in the sideBar are the same in the principal page");
        assertTrue(resultsPage.checkSecondResultTexts());
        log.info("accepting second flight option");
        resultsPage.clickContinueButton();
        log.info("Closing pop up");
        resultsPage.closePopUp();
    }
}
