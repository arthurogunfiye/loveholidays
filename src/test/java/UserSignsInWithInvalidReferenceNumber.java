package test.java;

import static facilities.LogReporter.logFailure;
import static facilities.LogReporter.logPrint;
import static facilities.Utilities.waitUntilElementIsDisplayed;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import facilities.TestCaseObject;
import testData.FeTestData;

/**
 * @author Arthur Ogunfuye
 */
public class UserSignsInWithInvalidReferenceNumber extends TestCaseObject {

	/**
	 * <p>Verify error message is displayed when a user attempts to
	 * sign in with an invalid reference number.<p>
	 * @param expectedPageTitle
	 * @param referenceNumber
	 * @param surname
	 */
	@Test(dataProvider = "userEntersInvalidReferenceNumber2", dataProviderClass = FeTestData.class)
    public void scenarioTwoTest(String expectedPageTitle, String referenceNumber,
    		String surname) {

        page.homePage().go();

        String pageTitle = driver.getTitle();
        assertEquals(pageTitle, expectedPageTitle);

        page.homePage().manageMyBookingMenu.click();
        waitUntilElementIsDisplayed(page.manageMyBookingPage().pageHeading);

        page.manageMyBookingPage().fillAndSubmitManageMyBookingForm(referenceNumber, surname);

        try {
            assertTrue(page.manageMyBookingPage().invalidReferenceNumberErrorMessage.isDisplayed());
            logPrint("Error message displayed contains: " + page.manageMyBookingPage().invalidReferenceNumberErrorMessage.getText());
        } catch (Exception e) {
        	logFailure("Error message is not displayed");
        }

        logPrint("Test completed");
	}
}
