package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = "#opc-shipping")
public class ShippingAddressComponent extends Component {
    private static final By continueBtnSel = By.cssSelector(".new-address-next-step-button");

    public ShippingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnContinueBtn(){
        findElement(continueBtnSel).click();
    }
}
