package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWait {
    // time in seconds for waits methods.

    public final long SHORT_WAIT_SECONDS = 5;
    public final long NORMAL_WAIT_SECONDS = 15;
    public final long MEDIUM_WAIT_SECONDS = 40;
    public final long LARGE_WAIT_SECONDS = 60;

    public void waitWebElementToBeClickable(WebDriver driver, WebElement Element,long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(Element));
    }

    public void waitWebElementVisibility(WebDriver driver, WebElement Element,long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(Element));
    }

}
