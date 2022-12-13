package test.computer;

import models.components.order.StandardComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.CreditCardType;
import test_data.PaymentMethodType;
import test_data.computer.ComputerData;
import test_data.DataObjectBuilder;
import test_flows.computer.OrderComputerFlow;
import test.BaseTest;
import url.Urls;

import java.security.SecureRandom;

public class BuyingStandardComputerTest extends BaseTest implements Urls {
    @Test(dataProvider = "computerData")
    public void testStandardComputerBuying(ComputerData computerData){
        WebDriver driver = getDriver();
        driver.get(BASE_URL.concat(BUY_STANDARD_COMPUTER_SLUG));
        int randomQuantity = new SecureRandom().nextInt(100) + 2;
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, StandardComputerComponent.class, computerData, randomQuantity);
        orderComputerFlow.buildComputerSpecAndAddToCart();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTOSAndCheckOut();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethodType.CREDIT_CARD);
        orderComputerFlow.inputCreditCardPaymentInformation(CreditCardType.DISCOVER);
        orderComputerFlow.confirmOrder();
        orderComputerFlow.verifyCheckoutCompleteInfo();
    }

    @DataProvider
    public ComputerData[] computerData(){
        String fileLocation = "/src/main/java/test_data/computer/StandardComputerDataList.json";
        ComputerData[] computerDataList = DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        return computerDataList;
    }
}
