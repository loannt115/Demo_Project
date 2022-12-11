package tests.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.CreditCardType;
import test_data.PaymentMethodType;
import test_data.computer.ComputerData;
import test_data.DataObjectBuilder;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

import java.security.SecureRandom;

public class BuyingCheapComputerTest extends BaseTest implements Urls {
    @Test(dataProvider = "computerData")
    public void testCheapComputerBuying(ComputerData computerData){
        driver.get(BASE_URL.concat(BUY_CHEAP_COMPUTER_SLUG));
        int randomQuantity = new SecureRandom().nextInt(100) + 2;
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, CheapComputerComponent.class, computerData, randomQuantity);
        orderComputerFlow.buildComputerSpecAndAddToCart();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTOSAndCheckOut();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethodType.CREDIT_CARD);
        orderComputerFlow.inputCreditCardPaymentInformation(CreditCardType.VISA);
        orderComputerFlow.confirmOrder();
        orderComputerFlow.verifyCheckoutCompleteInfo();
    }

    @DataProvider
    public ComputerData[] computerData(){
        String fileLocation = "/src/test/java/test_data/computer/CheapComputerDataList.json";
        ComputerData[] computerDataList = DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        return computerDataList;
    }
}
