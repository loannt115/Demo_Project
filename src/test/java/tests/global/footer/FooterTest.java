package tests.global.footer;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.global.footer.FooterTestFlow;
import url.Urls;

public class FooterTest {
    @Test
    public void testHomePageFooter(){
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get(Urls.BASE_URL);
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyFooterComponent();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testLoginPageFooter(){
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get(Urls.BASE_URL.concat(Urls.LOGIN_SLUG));
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyFooterComponent();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    //    @Test
//    public void testCategoryPageFooter(){
//        WebDriver driver = DriverFactory.getChromeDriver();
//        try {
//            driver.get(Urls.BASE_URL);
//            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
//            footerTestFlow.verifyFooterComponent();
//        }catch (Exception e){
//            e.printStackTrace();
//        } finally {
//            driver.quit();
//        }
//    }
//
}
