package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector(value = "#opc-payment_method")
public class PaymentMethodComponent extends Component {
    private final static By cashOnDeliverySel = By.cssSelector("input[value='Payments.CashOnDelivery']");
    private final static By checkMoneyOrderSel = By.cssSelector("input[value='Payments.CheckMoneyOrder']");
    private final static By creditCardSel = By.cssSelector("input[value='Payments.Manual']");
    private final static By purchaseOrderSel = By.cssSelector("input[value='Payments.PurchaseOrder']");
    private final static By continueBtnSel = By.cssSelector("input.payment-method-next-step-button");

    public PaymentMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCODMethod(){
        findElement(cashOnDeliverySel).click();
    }

    public void selectCheckMoneyOrderMethod(){
        findElement(checkMoneyOrderSel).click();
    }

    public void selectCreditCardMethod(){
        findElement(creditCardSel).click();
    }

    public void selectPurchaseOrderMethod(){
        findElement(purchaseOrderSel).click();
    }

    public void clickOnContinueBtn(){
        WebElement continueBtnElem = findElement(continueBtnSel);
        continueBtnElem.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtnElem));
    }
}
