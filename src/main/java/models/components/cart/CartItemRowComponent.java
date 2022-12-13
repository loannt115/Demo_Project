package models.components.cart;
import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".cart-item-row")
public class CartItemRowComponent extends Component {
    private static final By unitPriceSel = By.cssSelector(".product-unit-price");
    private static final By quantityInputSel = By.cssSelector("input[name^='itemquantity']");
    private static final By subTotalSel = By.cssSelector(".product-subtotal");

    public CartItemRowComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double unitPrice(){
        String numberStr = findElement(unitPriceSel).getText().trim();
        return Double.parseDouble(numberStr);
    }

    public double quantity(){
        String numberStr = findElement(quantityInputSel).getAttribute("value").trim();
        return Double.parseDouble(numberStr);
    }

    public double subTotal(){
        String numberStr = findElement(subTotalSel).getText().trim();
        return Double.parseDouble(numberStr);
    }
}
