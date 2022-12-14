package models.pages;

import models.components.order.ComputerEssentialComponent;
import org.openqa.selenium.WebDriver;

public class ComputerItemDetailsPage extends BasePage {
    public ComputerItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    public <T extends ComputerEssentialComponent> T computerComp(Class<T> computerEssentialCompClass) {
        return findComponent(computerEssentialCompClass, driver);
    }
}
