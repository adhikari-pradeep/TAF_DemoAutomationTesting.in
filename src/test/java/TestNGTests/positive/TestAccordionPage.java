package TestNGTests.positive;

import exception.UnsupportedBrowserException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AccordionPage;
import utility.BrowserFactory;

import java.io.IOException;

public class TestAccordionPage {

    private WebDriver driver;
    private AccordionPage accordionPage;

    private static final String GROUP_ONE_TEXT = "This Automation Testing API is very simple to read and understand. Every method in this is self explanatory. If a layman looks into this code/script can understand what is happening. You can read the automation script like manual test case.";
    private static final String GROUP_TWO_TEXT = "In this Automation tool, each and every functionality will be achieved by Single line code. i.e. Selecting a Value from dropdown, Switching between windows and Drag and Drop functionality etc...";
    private static final String GROUP_THREE_TEXT = "As the name indicates, you can chain the methods without breaking the code i.e. you can write the code for each element continuously.";
    private static final String GROUP_FOUR_TEXT = "NTest your web application across the most popular browsers including Firefox,Chrome and Internet Explorer to validate the functionality. You can easily switch between the browsers without changing the code.";


    @BeforeClass
    public void setup() throws IOException, UnsupportedBrowserException {
        driver = BrowserFactory.getWebDriver();
        accordionPage = AccordionPage.getInstance(driver);
    }

    @Test
    public void testLoadAccordionPage() {
        accordionPage.loadPage();
        Assert.assertTrue(accordionPage.isAccordionPageLoaded());
    }

    @Test(dependsOnMethods = "testLoadAccordionPage", priority = 1)
    public void testFirstGroup() {
        Assert.assertTrue(accordionPage.isGroupExpanded(1));
        accordionPage.expandGroup(1);
        Assert.assertTrue(accordionPage.isGroupExpanded(1));
        Assert.assertEquals(accordionPage.getGroupText(1), GROUP_ONE_TEXT);
        accordionPage.collapseGroup(1);
        Assert.assertFalse(accordionPage.isGroupExpanded(1));
    }

    @Test(dependsOnMethods = "testLoadAccordionPage", priority = 2)
    public void testSecondGroup() {
        Assert.assertFalse(accordionPage.isGroupExpanded(2));
        accordionPage.expandGroup(2);
        Assert.assertTrue(accordionPage.isGroupExpanded(2));
        Assert.assertEquals(accordionPage.getGroupText(2), GROUP_TWO_TEXT);
        accordionPage.collapseGroup(2);
        Assert.assertFalse(accordionPage.isGroupExpanded(2));
    }

    @Test(dependsOnMethods = "testLoadAccordionPage", priority = 3)
    public void testThirdGroup() {
        Assert.assertFalse(accordionPage.isGroupExpanded(3));
        accordionPage.expandGroup(3);
        Assert.assertTrue(accordionPage.isGroupExpanded(3));
        Assert.assertEquals(accordionPage.getGroupText(3), GROUP_THREE_TEXT);
        accordionPage.collapseGroup(3);
        Assert.assertFalse(accordionPage.isGroupExpanded(3));
    }

    @Test(dependsOnMethods = "testLoadAccordionPage", priority = 4)
    public void testFourthGroup() {
        Assert.assertFalse(accordionPage.isGroupExpanded(4));
        accordionPage.expandGroup(4);
        Assert.assertTrue(accordionPage.isGroupExpanded(4));
        Assert.assertEquals(accordionPage.getGroupText(4), GROUP_FOUR_TEXT);
        accordionPage.collapseGroup(4);
        Assert.assertFalse(accordionPage.isGroupExpanded(4));
    }

    @AfterClass
    public void cleanup() {
        accordionPage.deleteInstance();
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
