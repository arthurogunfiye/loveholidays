package facilities;

import org.testng.Reporter;

/**
 * Add logs into testng reporter.
 *
 */
public class LogReporter {

    /**
     * Add header log.
     * @param result log message.
     */
    public static void logHeader(final String result) {
        log("\n" + "## " + result);
    }

    /**
     * Print comment log.
     * @param result log message.
     */
    public static void logPrint(final String result) {
        log("- " + result);
    }

    /**
     * Add failure log.
     * @param result log message.
     * @exception Exception throw exception
     */
    public static void logFailure(final String result) {

        String message = "~ FAILURE! " + result;
        log(message);
    }

    /**
     * add log to testng reporter and output log on console.
     * @param result log message.
     */
    private static void log(final String result) {
        Reporter.log(result);
        System.out.println(result);
    }
}
