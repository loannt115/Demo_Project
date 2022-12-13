package models.components.global.footer;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".column.follow-us")
public class FollowUsColumn extends FooterColumComponent{
    public FollowUsColumn(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
