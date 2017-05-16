package appPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import facilities.TestInit;

/**
 * @author Arthur Ogunfuye
 */
public class HomePage {

	public WebDriver driver;

    /**
     * @param driver WebDriver driver instance
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = ".manage-booking>.masthead-badge>a>.masthead-label")
    public WebElement manageMyBookingMenu;

    /**
     * Navigate to Home Page.
     * @return HomePage
     */
    public HomePage go() {
        driver.get(TestInit.webUrl);
        return this;
    }
}
