package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import test_data.CreditCardType;

@ComponentCssSelector(value = "#opc-payment_info")
public class PaymentInformationComponent extends Component {
    private final static By creditCardTypeSel = By.id("CreditCardType");
    private final static By cardholderNameSel = By.id("CardholderName");
    private final static By cardNumberSel = By.id("CardNumber");
    private final static By expireMonthSel = By.id("ExpireMonth");
    private final static By expireYearSel = By.id("ExpireYear");
    private final static By cardCodeSel = By.id("CardCode");
    private final static By continueBtnSel = By.cssSelector(".payment-info-next-step-button");

    public PaymentInformationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCreditCardType(CreditCardType creditCardType){
        if (creditCardType == null) throw new IllegalArgumentException("[ERR] Credit card type can't be null!");
        Select select = new Select(findElement(creditCardTypeSel));
        switch (creditCardType){
            case VISA:
                select.selectByVisibleText("Visa");
                break;
            case MASTER_CARD:
                select.selectByVisibleText("Master card");
                break;
            case DISCOVER:
                select.selectByVisibleText("Discover");
                break;
            case AMEX:
                select.selectByVisibleText("Amex");
                break;
        }
    }

    public void inputCardholderName(String value){
        findElement(cardholderNameSel).sendKeys(value);
    }

    public void inputCardNumber(String value){
        findElement(cardNumberSel).sendKeys(value);
    }

    public void selectExpireMonth(String month){
        Select select = new Select(findElement(expireMonthSel));
        select.selectByValue(month);
    }

    public void selectExpireYear(String year){
        Select select = new Select(findElement(expireYearSel));
        select.selectByValue(year);
    }

    public void inputCardCode(String value){
        findElement(cardCodeSel).sendKeys(value);
    }

    public void clickOnContinueBtn(){
        WebElement continueBtnElem = findElement(continueBtnSel);
        continueBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtnElem));
    }
}
