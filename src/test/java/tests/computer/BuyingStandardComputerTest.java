package tests.computer;

import models.components.order.StandardComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.computer.ComputerData;
import test_data.DataObjectBuilder;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {
    @Test(dataProvider = "computerData")
    public void testCheapComputerBuying(ComputerData computerData){
        driver.get(BASE_URL.concat(BUY_STANDARD_COMPUTER_SLUG));
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, StandardComputerComponent.class, computerData);
        orderComputerFlow.buildComputerSpecAndAddToCart();
    }

    @DataProvider
    public ComputerData[] computerData(){
        String fileLocation = "/src/test/java/test_data/computer/StandardComputerDataList.json";
        ComputerData[] computerDataList = DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        return computerDataList;
    }
}