package facilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * <p>This class initialises test case for environment and driver.
 * All the data comes from config.xml`.<p>
 */
public class TestInit {

    private WebDriver driver;
    public static String webUrl;
    public static String env;
    public static Properties config;


    /**
     * Select driver.
     * @param browser Browser in which to run test
     * @param os Operating system
     * @param version Browser version
     * @param name Name or title of the test
     * @param osVersion Version of the OS
     * @throws Exception Throws exception
     */
    private void selectDriver(String browser, String os, String version, String name,
            String osVersion) throws Exception {

        switch (browser) {
        case "ie":
            System.setProperty("webdriver.ie.driver", config.getProperty("ieDriver"));
            DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            setDriver(new InternetExplorerDriver(caps)); break;
        case "firefox":
            ProfilesIni profile = new ProfilesIni();
            FirefoxProfile myProfile = profile.getProfile("Selenium");
            System.setProperty("webdriver.gecko.driver", config.getProperty("geckoDriver"));
            setDriver(new FirefoxDriver(myProfile)); break;
        case "chrome":
            ChromeOptions options = new ChromeOptions();
            options.addArguments("chrome.switches", "--disable-extensions");
            options.addArguments("--disable-popup-blocking");
            System.setProperty("webdriver.chrome.driver", config.getProperty("chromeDriver"));
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
            setDriver(new ChromeDriver(options));
            break;
        default:  System.out.println("No such driver: " + browser); break;
        }
    }

    /**
     * This method is used to load config.xml and set parameters for specific environment.
     * @param environmentName is DEV,STAGE,LIVE.
     * @throws Exception throw exception
     */
    private void setEnvironment(final String environmentName) throws Exception {

        env = environmentName;

        if (env.equalsIgnoreCase("live")) {
            webUrl = config.getProperty("liveUrl");
        }
    }

    /**
     * Class structure. Pass in driver and environment.
     * @param browser Browser in which to run test
     * @param os Operating system
     * @param version Browser version
     * @param environment Environment in which to run the test. Dev, Staging or Live.
     * @param name Name or title of the test
     * @param osVersion Version of the OS
     * @throws Exception throw exception
     */
    public TestInit(String browser, String os, String version, String environment,
            String name, String osVersion) throws Exception {
        config = loadConfig();
        setEnvironment(environment);

        if (browser.equals("") || browser.equalsIgnoreCase("no")) {
            driver = null;
        } else {
            selectDriver(browser, os, version, name, osVersion);
        }
    }

    /**
     *
     * @param driver This parameter will accept the WebDriver
     *  instance like FirefoxDriver, IEDriver or ChromeDriver
     *  Also, the implicitlyWait method will be called to wait for an element to appear within 30 seconds.
     */
    public final void setDriver(final WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
        this.driver.get(webUrl);
    }

    /**
     * If you want to run the test, you must use this method in order to use the methods in WebDriver.
     * @return Get WebDriver instance.
     */
    public final WebDriver getDriver() {
        return driver;
    }

    /**
     * Load config properties file.
     * @return Properties
     * @throws Exception Throws exception
     */
    private Properties loadConfig() throws Exception {

        Properties conf = new Properties();

        try {
            conf.load(new FileInputStream("configuration/config.properties"));
        } catch (IOException e) {
            LogReporter.logFailure("config.properties not found!");
        }
        return conf;
    }
}
