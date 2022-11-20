package tests;

import driver.DriverFactory;
import models.components.global.footer.*;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import url.Urls;

public class DemoHomePageTest implements Urls {

    @Test
    public void testFooter() {
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get(BASE_URL);

            HomePage homePage = new HomePage(driver);
            FooterComponent footerComponent = homePage.footerComp();
            InformationColumn informationColumn = footerComponent.informationColumn();
            CustomerServiceColumn customerServiceColumn = footerComponent.customerServiceColumn();
            MyAccountColumn myAccountColumn = footerComponent.myAccountColumn();
            FollowUsColumn followUsColumn = footerComponent.followUsColumn();

            System.out.println(informationColumn.headerElem().getText());
            System.out.println(customerServiceColumn.headerElem().getText());
            System.out.println(myAccountColumn.headerElem().getText());
            System.out.println(followUsColumn.headerElem().getText());
            System.out.println(followUsColumn.linksElem().get(0).getText());

            Thread.sleep(2000);
        } catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
