package models.components.order;

import io.qameta.allure.Step;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".product-essential")
public class CheapComputerComponent extends ComputerEssentialComponent {
    public CheapComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    @Step("Select Processor Type with value {type}")
    public String selectProcessorType(String type) {
        return selectComputerOption(type);
    }

    @Override
    @Step("Select RAM type with value {type}")
    public String selectRAMType(String type) {
        return selectComputerOption(type);
    }
}
