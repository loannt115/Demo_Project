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
    private int quantity = 1;
    private double totalItemPrice;

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

}
