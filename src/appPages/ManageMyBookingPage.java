package appPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Arthur Ogunfuye
 */
public class ManageMyBookingPage {

	public WebDriver driver;

    /**
     * @param driver WebDriver driver instance
     */
    public ManageMyBookingPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = ".mmb-login__heading")
    public WebElement pageHeading;

    @FindBy(id = "js-reference-field-with-letters")
    public WebElement referenceNumberField;

    @FindBy(css = "input[name='surname']")
    public WebElement surnameField;

    @FindBy(css = ".mmb-login__form__submit__button")
    public WebElement signInButton;

    @FindBy(css = ".mmb-login__form__field>.error:nth-of-type(1)")
    public WebElement invalidReferenceNumberErrorMessage;

    /**
     * Fill and submit the Manage My Bookings form.
     * @param referenceNumber
     * @param surname
     */
    public void fillAndSubmitManageMyBookingForm(String referenceNumber, String surname) {
    	referenceNumberField.sendKeys(referenceNumber);
    	surnameField.sendKeys(surname);
    	signInButton.click();
    }
}
