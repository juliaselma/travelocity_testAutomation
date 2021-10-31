package utils.dataproviders;

import org.testng.annotations.DataProvider;

public class SearchDataProvider {
    @DataProvider(name = "TestData")
    public static Object[][] getListOfDepartureAndDestination() {
        return new Object[][]{
                { "LAS","LAX","16 October 2021","23 October 2021" },
        };
    }
}
