package tests;

import org.testng.annotations.Test;
import pages.ResultsPage;
import pages.ReviewPage;
import utils.dataproviders.SearchDataProvider;


import static org.testng.Assert.assertTrue;

public class ReviewTests extends BaseTests{
    /**
     * Method testing elements in review page
     * @param departure
     * @param destination
     * @param departureDate
     * @param returnDate
     */
    @Test(dataProvider = "TestData",dataProviderClass = SearchDataProvider.class)
    public void reviewElementsVerification(String departure, String destination,String departureDate,String returnDate){
        log.info("Making a new search");
        homepage.makeYourSearch(departure, destination,departureDate,returnDate);
        ResultsPage resultsPage =homepage.submitSearch();
        log.info("Going to review page");
        ReviewPage reviewPage = resultsPage.goToReviewPage();

        log.info("Testing 'total price', 'departure and destination' and 'fare:economy'");
        assertTrue(reviewPage.isTripTotalPricePresent());
        assertTrue(reviewPage.areDepartureAndDestinationPresent());
        assertTrue(reviewPage.isFareSelectionEconomy());
    }
}
