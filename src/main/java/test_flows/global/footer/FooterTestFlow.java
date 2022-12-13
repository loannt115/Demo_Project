package test_flows.global.footer;

import models.components.global.footer.*;
import models.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {
    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }
    
    public void verifyFooterComponent(){
        BasePage basePage = new BasePage(driver);
        InformationColumn informationColumn = basePage.footerComp().informationColumn();
        CustomerServiceColumn customerServiceColumn = basePage.footerComp().customerServiceColumn();
        MyAccountColumn myAccountColumn = basePage.footerComp().myAccountColumn();
        FollowUsColumn followUsColumn = basePage.footerComp().followUsColumn();

        verifyInformationColumn(informationColumn);
        verifyCustomerServiceColumn(customerServiceColumn);
        verifyMyAccountColumn(myAccountColumn);
        verifyFollowUsColumn(followUsColumn);
    }

    private void verifyInformationColumn(FooterColumComponent footerColumComponent) {
        List<String> expectedLinkTexts = Arrays.asList(
                "Sitemap",
                "Shipping & Returns",
                "Privacy Notice",
                "Conditions of Use",
                "About us",
                "Contact us");
        List<String> expectedHrefs = Arrays.asList(
                "https://demowebshop.tricentis.com/sitemap",
                "https://demowebshop.tricentis.com/shipping-returns",
                "https://demowebshop.tricentis.com/privacy-policy",
                "https://demowebshop.tricentis.com/conditions-of-use",
                "https://demowebshop.tricentis.com/about-us",
                "https://demowebshop.tricentis.com/contactus");

        verifyFooterColumn(footerColumComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyCustomerServiceColumn(FooterColumComponent footerColumComponent) {
        List<String> expectedLinkTexts = Arrays.asList(
                "Search",
                "News",
                "Blog",
                "Recently viewed products",
                "Compare products list",
                "New products");
        List<String> expectedHrefs = Arrays.asList(
                "https://demowebshop.tricentis.com/search",
                "https://demowebshop.tricentis.com/news",
                "https://demowebshop.tricentis.com/blog",
                "https://demowebshop.tricentis.com/recentlyviewedproducts",
                "https://demowebshop.tricentis.com/compareproducts",
                "https://demowebshop.tricentis.com/newproducts");

        verifyFooterColumn(footerColumComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyMyAccountColumn(FooterColumComponent footerColumComponent) {
        List<String> expectedLinkTexts = Arrays.asList(
                "My account",
                "Orders",
                "Addresses",
                "Shopping cart",
                "Wishlist");
        List<String> expectedHrefs = Arrays.asList(
                "https://demowebshop.tricentis.com/customer/info",
                "https://demowebshop.tricentis.com/customer/orders",
                "https://demowebshop.tricentis.com/customer/addresses",
                "https://demowebshop.tricentis.com/cart",
                "https://demowebshop.tricentis.com/wishlist");

        verifyFooterColumn(footerColumComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyFollowUsColumn(FooterColumComponent footerColumComponent) {
        List<String> expectedLinkTexts = Arrays.asList(
                "Facebook",
                "Twitter",
                "RSS",
                "YouTube",
                "Google+");
        List<String> expectedHrefs = Arrays.asList(
                "http://www.facebook.com/nopCommerce",
                "https://twitter.com/nopCommerce",
                "https://demowebshop.tricentis.com/news/rss/1",
                "http://www.youtube.com/user/nopCommerce",
                "https://plus.google.com/+nopcommerce");

        verifyFooterColumn(footerColumComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyFooterColumn(FooterColumComponent footerColumComponent, List<String> expectedLinkTexts, List<String> expectedHrefs){
        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();

        if (footerColumComponent.linksElem().isEmpty())
            Assert.fail("[ERR] The column has no item to test.");

        for (WebElement link : footerColumComponent.linksElem()) {
            actualLinkTexts.add(link.getText().trim());
            actualHrefs.add(link.getAttribute("href"));
        }

        if (actualLinkTexts.isEmpty() || actualHrefs.isEmpty())
            Assert.fail("[ERR] Texts or hyperlinks is empty in the footer column");

            Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "[ERR] Link text list is mismatched!");
            Assert.assertEquals(actualHrefs, expectedHrefs, "[ERR] Hyperlink list is mismatched!");
    }
}
