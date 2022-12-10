package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = "#opc-shipping_method")
public class ShippingMethodComponent extends Component {
    By continueBtnSel = By.cssSelector(".shipping-method-next-step-button");

    public ShippingMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectShippingMethod(String value){
        String methodXpathSelector = "//label[contains(text(),'" + value + "')]";
        By methodXpathSel = By.xpath(methodXpathSelector);
        findElement(methodXpathSel).click();
    }

    public void clickOnContinueBtn(){
        findElement(continueBtnSel).click();

        try {
            Thread.sleep(2000);
        } catch (Exception ignore){}
    }
}
