package models.components.order;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public abstract class ComputerEssentialComponent extends BaseItemDetailsComponent {
    private static final By allOptionSel = By.cssSelector(".option-list input");

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void unselectAllDefaultOptions() {
        List<WebElement> allOptionElem = findElements(allOptionSel);
        allOptionElem.forEach(option -> {
            if (option.getAttribute("checked") != null) {
                option.click();
            }
        });
    }

    public abstract String selectProcessorType(String type);

    public abstract String selectRAMType(String type);

    @Step("Select OS with value {type}")
    public String selectOSType(String type) {
        return selectComputerOption(type);
    }

    @Step("Select HDD with value {type}")
    public String selectHDDType(String type) {
        return selectComputerOption(type);
    }

    @Step("Select Software with value {typeList}")
    public String[] selectSoftwareType(String[] typeList) {
        return selectComputerOption(typeList);
    }

    protected String selectComputerOption(String type) {
        String selectorStr = "//label[contains(text(), \"" + type + "\")]";
        By optionSel = By.xpath(selectorStr);
        WebElement optionElem = null;

        try {
            optionElem = component.findElement(optionSel);
        } catch (Exception ignored) {
        }

        if (optionElem != null) {
            optionElem.click();
            return optionElem.getText();
        } else {
            throw new RuntimeException("[ERR] The option: " + type + " is not existing.");
        }
    }

    protected String[] selectComputerOption(String[] typeList) {
        String[] typeListFull = new String[] {};
        List<String> typeListString = new ArrayList<String>();
        for (String type : typeList) {
            String selectorStr = "//label[contains(text(), \"" + type + "\")]";
            By optionSel = By.xpath(selectorStr);
            WebElement optionElem = null;

            try {
                optionElem = component.findElement(optionSel);
            } catch (Exception ignored) {
            }

            if (optionElem != null) {
                optionElem.click();
                typeListString.add(optionElem.getText());
            } else {
                throw new RuntimeException("[ERR] The option: " + type + " is not existing.");
            }
        }
        typeListFull = typeListString.toArray(typeListFull);
        return typeListFull;
    }
}
