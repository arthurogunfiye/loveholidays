package testData;

import org.testng.annotations.DataProvider;

public class FeTestData {

	/**
     * @return Test data
     */
    @DataProvider(name = "userEntersInvalidReferenceNumber1")
    public static Object[][] userEntersInvalidReferenceNumber1() {
        return new Object[][] {
            {"Loveholidays | Discover and book your perfect holiday",
            	"1702LCCCQV9H",
            	"Your booking reference starts with LVE or LOV and can be found on your booking summary."}
        };
    }

    /**
     * @return Test data
     */
    @DataProvider(name = "userEntersInvalidReferenceNumber2")
    public static Object[][] userEntersInvalidReferenceNumber2() {
        return new Object[][] {
            {"Loveholidays | Discover and book your perfect holiday", "LVE1702LCCCQV9H", "Test"}
        };
    }
}
