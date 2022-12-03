package models.pages;

import models.components.Component;
import models.components.global.BarNotificationComponent;
import models.components.global.footer.FooterComponent;
import models.components.global.header.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage extends Component {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver, driver.findElement(By.tagName("html")));
        this.driver = driver;
    }

    public BarNotificationComponent barNotificationComp(){
        return findComponent(BarNotificationComponent.class, driver);
    }

    public HeaderComponent headerComp(){
        return findComponent(HeaderComponent.class, driver);
    }

    public FooterComponent footerComp(){
        return findComponent(FooterComponent.class, driver);
    }
}
