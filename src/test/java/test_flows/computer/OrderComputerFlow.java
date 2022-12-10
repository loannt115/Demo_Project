package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.checkout.ShippingAddressComponent;
import models.components.checkout.ShippingMethodComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckoutOptionsPage;
import models.pages.CheckoutPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.DataObjectBuilder;
import test_data.computer.ComputerData;
import test_data.user.UserData;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {
    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private ComputerData computerData;
    private int quantity = 1;
    private double totalItemPrice;
    private UserData defaultCheckoutUser;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildComputerSpecAndAddToCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);

        // Unselect all default options
        computerEssentialComp.unselectAllDefaultOptions();

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAddedPrice = extractAdditionalPrice(processorFullStr);
        String RAMFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double RAMAddedPrice = extractAdditionalPrice(RAMFullStr);
        String HDDFullStr = computerEssentialComp.selectHDDType(computerData.getHdd());
        double HDDAddedPrice = extractAdditionalPrice(HDDFullStr);

        double OSAddedPrice = 0;
        if (computerData.getOs() != null) {
            String OSFullStr = computerEssentialComp.selectOSType(computerData.getOs());
            OSAddedPrice = extractAdditionalPrice(OSFullStr);
        }

        String[] softwareFulListlStr = computerEssentialComp.selectSoftwareType(computerData.getSoftware());
        double softwareAddedPrice = 0;
        for (String software : softwareFulListlStr) {
            softwareAddedPrice += extractAdditionalPrice(software);
        }

        if(this.quantity != 1){
            computerEssentialComp.inputQuantity(this.quantity);
        }

        double totalAddedPrice = processorAddedPrice + RAMAddedPrice + HDDAddedPrice + OSAddedPrice + softwareAddedPrice;
        totalItemPrice = (computerEssentialComp.basePrice() + totalAddedPrice) * this.quantity;

        // Add to cart
        computerEssentialComp.clickOnAddToCartBtn();
        computerItemDetailsPage.barNotificationComp().waitUntilItemAddedToCart();
        computerItemDetailsPage.barNotificationComp().clickOnCloseBtn();

        // Navigate to shopping cart
        computerItemDetailsPage.headerComp().clickOnShoppingCartLink();

        // DEBUG purpose only
        try{
            Thread.sleep(3000);
        } catch (Exception ignored){}
    }

    private static double extractAdditionalPrice(String itemStr){
        double price = 0;
        int factor = 1;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()){
            String priceStr = matcher.group(1);
            if (priceStr.startsWith("-")) factor = -1;
            price = Double.parseDouble(priceStr.replaceAll("[+-]",""));
        }
        return price * factor;
    }

    public void verifyShoppingCartPage(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowCompList = shoppingCartPage.cartItemRowCompList();
        if(cartItemRowCompList.isEmpty()){
            Assert.fail("[ERR] There is no item displayed in the shopping cart");
        }

        double allSubTotal = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemRowCompList) {
            double currentSubtotal = cartItemRowComp.subTotal();
            double expectedSubTotal = cartItemRowComp.quantity() * cartItemRowComp.unitPrice();
            Assert.assertEquals(currentSubtotal, expectedSubTotal, "[ERR] The subtotal on the item is incorrect");
            allSubTotal = allSubTotal + currentSubtotal;
        }
        TotalComponent totalComp = shoppingCartPage.totalComp();
        Map<String, Double> priceCategories = totalComp.priceCategories();
        double checkoutSubTotal = 0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal = 0;
        for (String priceType : priceCategories.keySet()) {
            double priceValue = priceCategories.get(priceType);
            if(priceType.startsWith("Sub-Total")){
                checkoutSubTotal = priceValue;
            } else if(priceType.startsWith("Total")){
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal = checkoutOtherFeesTotal + priceValue;
            }
        }

        Assert.assertEquals(allSubTotal, checkoutSubTotal, "[ERR] Checking out Subtotal value is incorrect");
        Assert.assertEquals((checkoutSubTotal + checkoutOtherFeesTotal), checkoutTotal, "[ERR] Checking out Total value is incorrect");
    }

    public void agreeTOSAndCheckOut(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComp().agreeTOS();
        shoppingCartPage.totalComp().clickOnCheckOutBtn();
        CheckoutOptionsPage checkoutOptionsPage = new CheckoutOptionsPage(driver);
        checkoutOptionsPage.checkOutAsGuest();
    }

    public void inputBillingAddress(){
        String defaultCheckoutUserLocation = "/src/test/java/test_data/user/DefaultCheckoutUser.json";
        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserLocation,UserData.class);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        BillingAddressComponent billingAddressComp = checkoutPage.billingAddressComp();
        billingAddressComp.selectInputNewAddress();
        billingAddressComp.inputFirstname(defaultCheckoutUser.getFirstName());
        billingAddressComp.inputLastname(defaultCheckoutUser.getLastName());
        billingAddressComp.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComp.inputCompany(defaultCheckoutUser.getCompany());
        billingAddressComp.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComp.selectState(defaultCheckoutUser.getState());
        billingAddressComp.inputCity(defaultCheckoutUser.getCity());
        billingAddressComp.inputAddress1(defaultCheckoutUser.getAddress1());
        billingAddressComp.inputAddress2(defaultCheckoutUser.getAddress2());
        billingAddressComp.inputZipCode(defaultCheckoutUser.getZipCode());
        billingAddressComp.inputPhoneNumber(defaultCheckoutUser.getPhoneNumber());
        billingAddressComp.inputFaxNumber(defaultCheckoutUser.getFaxNumber());
        billingAddressComp.clickOnContinueBtn();
    }

    public void inputShippingAddress(){
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ShippingAddressComponent shippingAddressComp = checkoutPage.shippingAddressComp();
        shippingAddressComp.clickOnContinueBtn();
    }

    public void selectShippingMethod(){
        List<String> shippingMethodList = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        int randomIndex = new SecureRandom().nextInt(shippingMethodList.size());
        String randomShippingMethod = shippingMethodList.get(randomIndex);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ShippingMethodComponent shippingMethodComp = checkoutPage.shippingMethodComp();
        shippingMethodComp.selectShippingMethod(randomShippingMethod);
        shippingMethodComp.clickOnContinueBtn();
    }

}
