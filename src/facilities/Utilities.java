package facilities;

import static facilities.LogReporter.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;

/**
 * This class is defined to provide some common methods.
 *
 */
public class Utilities {

    /**
     * Wait until element is displayed
     * Only wait for 10 seconds.
     * @param element element want to be waited
     * @throws Exception throw exception
     */
    public static void waitUntilElementIsDisplayed(final WebElement element) {

        logPrint("Waiting for this element to be displayed: " + element.getText());

        int wait = 0;
        while (true) {
            try {
                if (element.isDisplayed()) {
                    break;
                }
            } catch (Exception ex) {
                wait = wait + 1;
                if (wait > 9) {
                    try {
                        logFailure("Element " + element.getText() + " is not displayed");
                    } catch (Exception ex2) {

                    }
                } else {
                    sleep(1000);
                    continue;
                }
            }
        }
    }

    /**
     * Thread.sleep.
     * @param duration in milliseconds
     */
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {

        }
    }

    /**
     * Get the current time.
     * @return The time format would be yyyy-mm-dd hh:mm:ss.
     */
    public static String getTime() {
        SimpleDateFormat timePattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return timePattern.format(new Date());
    }
}
