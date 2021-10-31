package tests;

import org.testng.annotations.Test;
import pages.ResultsPage;
import utils.dataproviders.SearchDataProvider;

import static org.testng.Assert.assertTrue;


public class FirstFlightResultTests extends BaseTests{
    /**
     * Method testing the results of the search
     * @param departure
     * @param destination
     * @param departureDate
     * @param returnDate
     */
    @Test(dataProvider = "TestData",dataProviderClass = SearchDataProvider.class)
    public void compareSideBarWithResults(String departure, String destination,String departureDate,String returnDate){
        log.info("Making a new search");
        homepage.makeYourSearch(departure, destination,departureDate,returnDate);
        ResultsPage resultsPage = homepage.submitSearch();

        log.info("Testing the elements on de sideBar are the same in the principal page");
        assertTrue(resultsPage.checkFirstResultTexts());
        log.info("Accepting the first flight option");
        resultsPage.clickContinueButton();
    }
}
