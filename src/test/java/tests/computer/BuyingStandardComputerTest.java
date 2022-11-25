package tests.computer;

import models.components.order.StandardComputerComponent;
import org.testng.annotations.Test;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {
    @Test
    public void testCheapComputerBuying(){
        driver.get(BASE_URL.concat(BUY_STANDARD_COMPUTER_SLUG));

        OrderComputerFlow<StandardComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, StandardComputerComponent.class);

        orderComputerFlow.buildComputerSpecAndAddToCart();
    }
}
