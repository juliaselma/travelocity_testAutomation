package tests;


import org.testng.annotations.Test;
import pages.ResultsPage;
import utils.dataproviders.SearchDataProvider;

import static org.testng.Assert.assertTrue;

public class SortShortestDurationFlightsTests extends BaseTests {

    /**
     * Method testing the results are correctly sorted by 'shortest flight duration'
     * @param departure
     * @param destination
     * @param departureDate
     * @param returnDate
     */
    @Test (dataProvider = "TestData",dataProviderClass = SearchDataProvider.class)
    public void correctSortTest(String departure, String destination,String departureDate,String returnDate){
        log.info("Making a new search");
        homepage.makeYourSearch(departure, destination,departureDate,returnDate);
        ResultsPage resultsPage =homepage.submitSearch();

        log.info("Testing flight duration is correctly sorted by shortest");
        assertTrue(resultsPage.sortByDuration());
    }
}
