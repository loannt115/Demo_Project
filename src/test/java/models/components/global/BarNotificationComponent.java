package models.components.global;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector("#bar-notification")
public class BarNotificationComponent extends Component {
    private static final By closeBtnSel = By.cssSelector("span[class='close']");
    private static final By contentSel = By.tagName("p");

    public BarNotificationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnCloseBtn(){
        findElement(closeBtnSel).click();
        wait.until(ExpectedConditions.invisibilityOf(this.getComponent()));
    }

    public String notificationContent(){
        return notificationElem().getText().trim();
    }

    private WebElement notificationElem(){
        return findElement(contentSel);
    }

    public void waitUntilItemAddedToCart() {
        String successMsg = "The product has been added to your shopping cart";
        wait.until(ExpectedConditions.textToBePresentInElementLocated(contentSel, successMsg));
    }
}
