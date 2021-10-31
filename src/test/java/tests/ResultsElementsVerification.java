package tests;

import org.testng.annotations.Test;
import pages.ResultsPage;
import utils.dataproviders.SearchDataProvider;


import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;


public class ResultsElementsVerification extends BaseTests{
    /**
     * Method testing correct number of elements in combo box
     * @param departure
     * @param destination
     * @param departureDate
     * @param returnDate
     */
    @Test (dataProvider = "TestData",dataProviderClass = SearchDataProvider.class,priority = 1)
    public void comboBoxNumberOfElementsTest(String departure, String destination, String departureDate,String returnDate){
        log.info("Making a new search");
        homepage.makeYourSearch(departure, destination,departureDate,returnDate);
        ResultsPage resultsPage =homepage.submitSearch();

        log.info("Testing number o options in the combo box");
        assertEquals(resultsPage.checkNumberOfBoxElements(),8);
    }

    /**
     * Method testing option names available in the combo box
     * @param departure
     * @param destination
     * @param departureDate
     * @param returnDate
     */
    @Test(dataProvider = "TestData",dataProviderClass = SearchDataProvider.class, priority = 2)
    public void checkBoxOptionsName(String departure, String destination,String departureDate,String returnDate){
        log.info("Making a new search");
        homepage.makeYourSearch(departure, destination,departureDate,returnDate);
        ResultsPage resultsPage =homepage.submitSearch();

        log.info("Testing the options contain 'price','duration','departure' and 'arrival'");
        assertTrue(resultsPage.checkNameOfBoxElements().contains("Price"));
        assertTrue(resultsPage.checkNameOfBoxElements().contains("Duration"));
        assertTrue(resultsPage.checkNameOfBoxElements().contains("Departure"));
        assertTrue(resultsPage.checkNameOfBoxElements().contains("Arrival"));

    }

    /**
     * Method testing every results has 'departure and arrival', 'airlines','price' and 'flight duration' fields
     * @param departure
     * @param destination
     * @param departureDate
     * @param returnDate
     */
    @Test (dataProvider = "TestData",dataProviderClass = SearchDataProvider.class)
    public void checkCorrectSearchResults(String departure, String destination,String departureDate,String returnDate){
        log.info("Making a new search");
        homepage.makeYourSearch(departure, destination,departureDate,returnDate);
        ResultsPage resultsPage = homepage.submitSearch();

        log.info("Testing results contain: 'departure and arrival', 'airlines','price' and 'flight duration'");
        assertTrue(resultsPage.checkDepartureArrivalInResults());
        assertTrue(resultsPage.checkAirlineInResults());
        assertTrue(resultsPage.checkPriceInResults());
        assertTrue(resultsPage.checkDurationInResults());
    }
}
