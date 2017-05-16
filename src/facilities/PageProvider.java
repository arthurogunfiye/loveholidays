package facilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import appPages.*;

/**
 * @author Arthur Ogunfuye
 * <p>This PageFactory class initialises every page
 * and the elements exposed in each page.<p>
 */
public class PageProvider {

	private WebDriver driver;

	public PageProvider(WebDriver driver)
    {
        this.driver = driver;
    }

	/**
     * Initialise the Home page and its elements.
     * @param driver
     * @return Home Page
     */
    public HomePage homePage() {
        return PageFactory.initElements(driver, HomePage.class);
    }

    /**
     * Initialise the Manage My Booking page and its elements.
     * @param driver
     * @return Manage My Booking page
     */
    public ManageMyBookingPage manageMyBookingPage() {
        return PageFactory.initElements(driver, ManageMyBookingPage.class);
    }
}
