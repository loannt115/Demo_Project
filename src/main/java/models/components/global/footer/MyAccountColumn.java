package models.components.global.footer;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".column.my-account")
public class MyAccountColumn extends FooterColumComponent{
    public MyAccountColumn(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
