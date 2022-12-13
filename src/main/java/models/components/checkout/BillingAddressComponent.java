package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@ComponentCssSelector(value = "#opc-billing")
public class BillingAddressComponent extends Component {
    private static final By billingAddDropdownSel = By.id("billing-address-select");
    private static final By firstNameSel = By.id("BillingNewAddress_FirstName");
    private static final By lastNameSel = By.id("BillingNewAddress_LastName");
    private static final By emailSel = By.id("BillingNewAddress_Email");
    private static final By companySel = By.id("BillingNewAddress_Company");
    private static final By countrySel = By.id("BillingNewAddress_CountryId");
    private static final By stateLoadingProgressSel = By.id("states-loading-progress");
    private static final By stateSel = By.id("BillingNewAddress_StateProvinceId");
    private static final By citySel = By.id("BillingNewAddress_City");
    private static final By address1Sel = By.id("BillingNewAddress_Address1");
    private static final By address2Sel = By.id("BillingNewAddress_Address2");
    private static final By zipCodeSel = By.id("BillingNewAddress_ZipPostalCode");
    private static final By phoneNumberSel = By.id("BillingNewAddress_PhoneNumber");
    private static final By faxNumberSel = By.id("BillingNewAddress_FaxNumber");
    private static final By continueBtnSel = By.cssSelector("input[class*='new-address-next-step-button']");

    public BillingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectInputNewAddress(){
        if(!findElements(billingAddDropdownSel).isEmpty()){
            Select select = new Select(findElement(billingAddDropdownSel));
            select.selectByVisibleText("New Address");
        }
    }

    public void inputFirstname(String value){
        findElement(firstNameSel).sendKeys(value);
    }

    public void inputLastname(String value){
        findElement(lastNameSel).sendKeys(value);
    }

    public void inputEmail(String value){
        findElement(emailSel).sendKeys(value);
    }

    public void inputCompany(String value){
        findElement(companySel).sendKeys(value);
    }

    public void selectCountry(String value){
        Select select = new Select(findElement(countrySel));
        select.selectByVisibleText(value);
    }

    public void selectState(String value){
        Select select = new Select(findElement(stateSel));
        select.selectByVisibleText(value);
    }

    public void inputCity(String value){
        findElement(citySel).sendKeys(value);
    }

    public void inputAddress1(String value){
        findElement(address1Sel).sendKeys(value);
    }

    public void inputAddress2(String value){
        findElement(address2Sel).sendKeys(value);
    }

    public void inputZipCode(String value){
        findElement(zipCodeSel).sendKeys(value);
    }

    public void inputPhoneNumber(String value){
        findElement(phoneNumberSel).sendKeys(value);
    }

    public void inputFaxNumber(String value){
        findElement(faxNumberSel).sendKeys(value);
    }

    public void clickOnContinueBtn(){
        WebElement continueBtnElem = findElement(continueBtnSel);
        continueBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtnElem));
    }
}
