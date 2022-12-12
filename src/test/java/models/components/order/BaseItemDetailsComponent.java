package models.components.order;

import io.qameta.allure.Step;
import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseItemDetailsComponent extends Component {
    private static final By inputQuantitySel = By.cssSelector(".qty-input");
    private static final By basePriceSel = By.cssSelector(".product-price");
    private static final By addToCartBtnSel = By.cssSelector("[id^='add-to-cart-button']");

    public BaseItemDetailsComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Step("Getting base price")
    public double basePrice(){
        String productPriceText = component.findElement(basePriceSel).getText().trim();
        return Double.parseDouble(productPriceText);
    }

    @Step("Input Quantity with value {quantity}")
    public void inputQuantity(int quantity){
        WebElement quantityElem = findElement(inputQuantitySel);
        quantityElem.clear();
        quantityElem.sendKeys(String.valueOf(quantity));
    }

    @Step("Click on Add to cart button")
    public void clickOnAddToCartBtn(){
        findElement(addToCartBtnSel).click();
    }
}
