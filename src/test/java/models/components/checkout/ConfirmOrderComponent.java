package models.components.checkout;

import models.components.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmOrderComponent extends Component {
    public ConfirmOrderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
