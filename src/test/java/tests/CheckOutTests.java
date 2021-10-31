package tests;

import org.testng.annotations.Test;
import pages.CheckOutPage;
import pages.ResultsPage;
import pages.ReviewPage;
import utils.dataproviders.CheckOutDataProvider;


import static org.testng.Assert.assertTrue;

public class CheckOutTests extends BaseTests{
    /**
     * Method testing checkout functions:
     * @param departure
     * @param destination
     * @param departureDate
     * @param returnDate
     * @param name
     * @param middleName
     * @param lastName
     * @param phone
     * @param numberOfOptions
     * @param date
     * @param month
     * @param year
     * @param secondPassengerName
     * @param secondPMiddleName
     * @param secondPLastName
     * @param SPDateBirth
     * @param SPMonthBirth
     * @param SPYearBirth
     */
    @Test(dataProvider = "TestData",dataProviderClass = CheckOutDataProvider.class)
    public void checkOutTests(String departure, String destination,String departureDate,String returnDate, String name, String middleName, String lastName, String phone,int numberOfOptions ,String date,String month, String year, String secondPassengerName,String secondPMiddleName,String secondPLastName,String SPDateBirth, String SPMonthBirth, String SPYearBirth ){
        log.info("Making a new search");
        homepage.makeYourSearch(departure, destination,departureDate,returnDate);
        log.info("Selecting flights");
        ResultsPage resultsPage = homepage.submitSearch();
        log.info("Going to review page");
        ReviewPage reviewPage = resultsPage.goToReviewPage();
        log.info("Testing checkout");
        CheckOutPage checkOutPage = reviewPage.pressCheckOutButton();
        checkOutPage.completeFirstPassengerInfo(name,middleName,lastName,phone,date, month,year);
        checkOutPage.completeSecondPassengerInfo(secondPassengerName,secondPMiddleName,secondPLastName,SPDateBirth,SPMonthBirth,SPYearBirth);

        log.info("checking correct number of country code options");
        assertTrue(checkOutPage.numberOfCountryCodeOptions()== numberOfOptions);
        log.info("checking sign in with facebook button is enabled");
        assertTrue(checkOutPage.checkSignInFacebook());
        log.info("checking sign in with apple button is enabled");
        assertTrue(checkOutPage.checkSignInApple());
        log.info("checking insurance reconsideration");
        assertTrue(checkOutPage.reconsiderAfterNoInsurance());
        log.info("checking change insure option");
        assertTrue(checkOutPage.changeOption());
    }
}
