package tests.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.Test;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTest implements Urls {
    @Test
    public void testCheapComputerBuying(){
        driver.get(BASE_URL.concat(BUY_CHEAP_COMPUTER_SLUG));

        OrderComputerFlow<CheapComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, CheapComputerComponent.class);

        orderComputerFlow.buildComputerSpecAndAddToCart();
    }
}
