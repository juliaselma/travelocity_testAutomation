package utils.dataproviders;

import org.testng.annotations.DataProvider;

public class CheckOutDataProvider {
    @DataProvider(name = "TestData")
    public static Object[][] checkoutInfomation() {
        return new Object[][]{
                { "LAS","LAX","16 October 2021","23 October 2021","Julia","Laura","Selma","1150613030",232,"16","11 - Nov","1984","Sebastian","Gerardo","Marchi","24","01 - Jan","1980"},
        };
    }
}
