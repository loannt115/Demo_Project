package test_flows.computer;

import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import org.openqa.selenium.WebDriver;
import test_data.computer.ComputerData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {
    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private ComputerData computerData;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
    }

    public void buildComputerSpecAndAddToCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);
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

        double totalAddedPrice = processorAddedPrice + RAMAddedPrice + HDDAddedPrice + OSAddedPrice;
        System.out.println("totalAddedPrice: " + totalAddedPrice);
    }

    private static double extractAdditionalPrice(String itemStr){
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()){
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]",""));
        }
        return price;
    }

}
