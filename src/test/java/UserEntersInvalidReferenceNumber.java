package test.java;

import static facilities.Utilities.*;
import static facilities.LogReporter.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import facilities.TestCaseObject;
import testData.FeTestData;

/**
 * @author Arthur Ogunfuye
 */
public class UserEntersInvalidReferenceNumber extends TestCaseObject {

    /**
     * <p>Verify appropriate error message is displayed when
     * user enters invalid reference number.<p>
     * @param expectedPageTitle
     * @param referenceNumber
     * @param errorMessage
     */
	@Test(dataProvider = "userEntersInvalidReferenceNumber1", dataProviderClass = FeTestData.class)
    public void scenarioOneTest(String expectedPageTitle, String referenceNumber,
    		String errorMessage) {

        page.homePage().go();

        String pageTitle = driver.getTitle();
        assertEquals(pageTitle, expectedPageTitle);

        page.homePage().manageMyBookingMenu.click();
        waitUntilElementIsDisplayed(page.manageMyBookingPage().pageHeading);

        page.manageMyBookingPage().referenceNumberField.sendKeys(referenceNumber);
        page.manageMyBookingPage().signInButton.click();

        try {
            assertTrue(page.manageMyBookingPage().referenceNumberField.getAttribute("oninvalid").contains(errorMessage));
            logPrint("Error message tooltip is displayed which contains expected error text");
        } catch (Exception e) {
        	logFailure("Error message is not displayed");
        }

        logPrint("Test completed");
    } 
}
