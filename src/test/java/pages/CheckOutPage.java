package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CheckOutPage extends BasePage {

    /**
     * constructor method of the CheckOut class
     * @param driver
     */
    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstname[0]")
    private WebElement firstName;

    @FindBy(css = "input[data-tealeaf-name='middleName']")
    private WebElement middleName;

    @FindBy(id = "lastname[0]")
    private WebElement lastName;

    @FindBy(id = "country_code[0]")
    private WebElement country;

    @FindBy(css = "select[data-cko-rfrr-id='FLT.CKO.Phone.CountryCode'] option[data-country-iso='arg']")
    private WebElement selectedCountry;

    @FindBy(id = "phone-number[0]")
    private WebElement phoneNumber;

    @FindBy(id = "gender_female[0]")
    private WebElement gender;

    @FindBy(id = "date_of_birth_month0")
    private WebElement selectMonthOfBirth;

    @FindBy(id = "date_of_birth_day[0]")
    private WebElement selectDateOfBirth;

    @FindBy(id = "date_of_birth_year[0]")
    private WebElement selectYearOfBirth;

    @FindBy(css = "select[id='date_of_birth_month0']  option")
    List<WebElement>  monthOptions;

    @FindBy(css = "select[id='date_of_birth_day[0]'] option")
    List<WebElement>  dateOptions;

    @FindBy(css = "select[id='date_of_birth_year[0]']  option")
    List<WebElement>  yearOptions;

    @FindBy(css = "select[id='country_code[0]'] option")
    List<WebElement>  countryOptions;
    List<String> countryTexts = new ArrayList<>();

    @FindBy(id = "firstname[1]")
    private WebElement secondPassengerFirstName;

    @FindBy(id = "middlename[1]")
    private WebElement secondPassengerSecondName;

    @FindBy(id = "lastname[1]")
    private WebElement secondPassengerLastName;

    @FindBy(id = "gender_male[1]")
    private WebElement secondPassengerGender;

    @FindBy(id = "date_of_birth_month1")
    private WebElement selectSPMonthOfBirth;

    @FindBy(id = "date_of_birth_day[1]")
    private WebElement selectSPDateOfBirth;

    @FindBy(id = "date_of_birth_year[1]")
    private WebElement selectSPYearOfBirth;

    @FindBy(css = "select[id='date_of_birth_month1']  option")
    List<WebElement>  monthSPOptions;

    @FindBy(css = "select[id='date_of_birth_day[1]'] option")
    List<WebElement>  dateSPOptions;

    @FindBy(css = "select[id='date_of_birth_year[1]']  option")
    List<WebElement>  yearSPOptions;

    @FindBy(css = "div[id='primary-content'] a.login-module-title")
    private WebElement signInLink;

    @FindBy(id = "fb-social-dt")
    private WebElement signInFacebookButton;

    @FindBy(css = "div[id='login-module-toggle-top'] button.signin-login-apple-button")
    private WebElement signInAppleButton;

    @FindBy(id = "no_insurance")
    private WebElement noInsurance;

    @FindBy(id = "yes_insurance")
    private WebElement yesInsurance;

    @FindBy(css = "button[data-type='reconsider']")
    private WebElement reconsiderButton;

    @FindBy(id = "insurance-offering-url")
    private WebElement changeOption;


    /**
     * Method to enter the first passenger´s information to the form
     * @param name
     * @param middle
     * @param last
     * @param phone
     * @param date
     * @param month
     * @param year
     * params are received from data provider
     */
    public void completeFirstPassengerInfo(String name, String middle, String last, String phone,String date,String month, String year){
        firstName.sendKeys(name);
        middleName.sendKeys(middle);
        lastName.sendKeys(last);
        click(country, customWait.SHORT_WAIT_SECONDS);
        click(selectedCountry, customWait.SHORT_WAIT_SECONDS);
        phoneNumber.sendKeys(phone);
        click(gender, customWait.SHORT_WAIT_SECONDS);
        click(selectMonthOfBirth, customWait.SHORT_WAIT_SECONDS);
        selectMonth(month,monthOptions);
        click(selectDateOfBirth, customWait.SHORT_WAIT_SECONDS);
        selectDate(date,dateOptions);
        click(selectYearOfBirth, customWait.SHORT_WAIT_SECONDS);
        selectYear(year,yearOptions);
    }

    /**
     * Method to enter the second passenger´s information to the form
     * @param name
     * @param middleName
     * @param LastName
     * @param date
     * @param month
     * @param year
     * params are received from data provider
     */
    public void completeSecondPassengerInfo(String name,String middleName,String LastName,String date,String month, String year){
        secondPassengerFirstName.sendKeys(name);
        secondPassengerSecondName.sendKeys(middleName);
        secondPassengerLastName.sendKeys(LastName);
        click(secondPassengerGender, customWait.SHORT_WAIT_SECONDS);
        click(selectSPMonthOfBirth, customWait.SHORT_WAIT_SECONDS);
        selectMonth(month,monthSPOptions);
        click(selectSPDateOfBirth, customWait.SHORT_WAIT_SECONDS);
        selectDate(date,dateSPOptions);
        click(selectSPYearOfBirth, customWait.SHORT_WAIT_SECONDS);
        selectYear(year,yearSPOptions);
    }

    /**
     * Method to select a date birth for passengers
     * @param selectedDate
     * @param dateOptions
     */
    public void selectDate(String selectedDate, List<WebElement>dateOptions){
        iterateBirthOptions(selectedDate,dateOptions);
    }

    /**
     * Method to select passenger´s month of birth
     * @param selectedMonth
     * @param monthOptions
     */
    public void selectMonth(String selectedMonth,List<WebElement>monthOptions){
        iterateBirthOptions(selectedMonth,monthOptions);
    }

    /**
     * Method to select passenger´s year of birth
     * @param selectedYear
     * @param yearOptions
     */
    public void selectYear(String selectedYear,List<WebElement>yearOptions){
        iterateBirthOptions(selectedYear,yearOptions);
    }

    /**
     * Method to select passenger´s date of birth
     * @param selectedBirthDate
     * @param options
     */
    public void iterateBirthOptions(String selectedBirthDate,List<WebElement> options){
        for(WebElement option : options){
            String optionName = option.getText();
            if (optionName.equals(selectedBirthDate)) {
                click(option,customWait.SHORT_WAIT_SECONDS);
            }
        }
    }

    /**
     * Method that returns the number of country code available
     * @return int with the size of the list containing the country texts
     */
    public int numberOfCountryCodeOptions(){
        click(country, customWait.SHORT_WAIT_SECONDS);
        iterateOptions(countryOptions,countryTexts);
        return countryTexts.size() ;
    }

    /**
     * Method that checks the 'sign in with facebook account' button is enabled in the checkout page
     * @return boolean
     */
    public boolean checkSignInFacebook(){
        click(signInLink, customWait.NORMAL_WAIT_SECONDS);
        return signInFacebookButton.isEnabled();
    }

    /**
     * Method that checks the 'sign in with apple account' button is enabled in the checkout page
     * @return boolean
     */
    public boolean checkSignInApple(){
        click(signInLink,customWait.NORMAL_WAIT_SECONDS);
        return signInAppleButton.isEnabled();
    }

    /**
     * Method checking ´reconsider insurance button' is enable after selecting 'no insurance' option
     * @return boolean
     */
    public boolean reconsiderAfterNoInsurance(){
        click(noInsurance,customWait.NORMAL_WAIT_SECONDS);
        return reconsiderButton.isEnabled();
    }

    /**
     * Method checking 'change option' link is enabled after selecting 'yes insurance'
     * @return boolean
     */
    public boolean changeOption(){
        click(reconsiderButton,customWait.NORMAL_WAIT_SECONDS);
        click(yesInsurance,customWait.NORMAL_WAIT_SECONDS);
        return changeOption.isEnabled();
    }
}
