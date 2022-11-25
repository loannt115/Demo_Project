package test_flows.computer;

import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import org.openqa.selenium.WebDriver;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {
    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
    }

    public void buildComputerSpecAndAddToCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);
        computerEssentialComp.selectProcessorType("Fast");
        computerEssentialComp.selectRAMType("8 GB");

        try {
            Thread.sleep(5000);
        } catch (Exception e){}
    }
}
