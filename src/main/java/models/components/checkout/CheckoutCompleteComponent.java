package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".order-completed")
public class CheckoutCompleteComponent extends Component {
    private static final By messageSuccessSel = By.cssSelector(".title");
    private static final By orderNumberSel = By.xpath("(//li[contains(text(),'Order number: ')])");
    private static final By continueBtnSel = By.cssSelector(".order-completed-continue-button");

    public CheckoutCompleteComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public String messageOrderSuccess(){
        return findElement(messageSuccessSel).getText().trim();
    }

    public String orderNumber(){
        String orderNumberSentence =  findElement(orderNumberSel).getText().trim();
        return orderNumberSentence.substring(orderNumberSentence.indexOf(":") + 1).trim();
    }

    public void clickOnContinueBtn(){
        findElement(continueBtnSel).click();
    }
}
