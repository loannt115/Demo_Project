package models.pages;

import models.components.checkout.CheckoutCompleteComponent;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage{
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public CheckoutCompleteComponent checkoutCompleteComp(){
        return findComponent(CheckoutCompleteComponent.class, driver);
    }
}
