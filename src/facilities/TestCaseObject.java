package facilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * @author Arthur Ogunfuye
 */
public class TestCaseObject {
    public WebDriver driver;
    protected PageProvider page;
    protected String baseUrl;

    /**
     * Method to set up the test.
     * @param browserName Browser name
     * @param os Operating system
     * @param version Browser version
     * @param environment Environment in which to run the test e.g. Staging
     * @param osVersion Version of the OS
     * @throws Exception Throws exception
     */
    @Parameters({"browserName", "os", "version", "environment", "osVersion"})
    @BeforeClass
    protected void setUp(String browserName, String os, String version,
            String environment, String osVersion) throws Exception {
        String name = this.getClass().getName();
        LogReporter.logHeader("Running test *** " + name + " *** at " + Utilities.getTime() + " in " + environment);
        TestInit ti = new TestInit(browserName, os, version, environment, name, osVersion);
        this.driver = ti.getDriver();
        page = new PageProvider(driver);
        baseUrl = TestInit.webUrl;
    }

    /**
     * Method to tear down the test.
     * @throws Exception Throws exception
     */
    @AfterClass
    protected void tearDown() throws Exception {
        LogReporter.logPrint("Tear down!");

        if (driver == null) {
            return;
        }

        try {
            driver.close();
            driver.quit();
        } catch (Exception e) {
            LogReporter.logFailure("Could not close browser: " + e.getMessage());
        }
    }

    protected PageProvider pageProvider()
    {
        return new PageProvider(driver);
    }
}
