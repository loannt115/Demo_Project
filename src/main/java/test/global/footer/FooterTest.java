package test.global.footer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.global.footer.FooterTestFlow;
import test.BaseTest;
import url.Urls;

public class FooterTest extends BaseTest {
    @Test(groups = {"smoke"})
    public void testHomePageFooter() {
        WebDriver driver = getDriver();
        driver.get(Urls.BASE_URL);
//        Assert.fail("Demo screenshot...");
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test
    public void testLoginPageFooter() {
        WebDriver driver = getDriver();
        driver.get(Urls.BASE_URL.concat(Urls.LOGIN_SLUG));
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

}
