package models.components.global.footer;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".column.customer-service")
public class CustomerServiceColumn extends FooterColumComponent{
    public CustomerServiceColumn(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
