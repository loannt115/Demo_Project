package models.components.global.footer;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".footer")
public class FooterComponent extends Component {
    public FooterComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public InformationColumn informationColumn(){
        return findComponent(InformationColumn.class, driver);
    }

    public CustomerServiceColumn customerServiceColumn(){
        return findComponent(CustomerServiceColumn.class, driver);
    }

    public MyAccountColumn myAccountColumn(){
        return findComponent(MyAccountColumn.class, driver);
    }

    public FollowUsColumn followUsColumn(){
        return findComponent(FollowUsColumn.class, driver);
    }
}
