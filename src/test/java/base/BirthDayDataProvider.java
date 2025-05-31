package base;

import org.testng.annotations.DataProvider;
import utils.DateUtils;

public class BirthDayDataProvider {


    @DataProvider(name = "Adulthood")
    public static Object[][] adultBirthDatesProvider() {
        return new Object[][]{
                {DateUtils.getDate20YearsAgo()},  // 20 years
                {DateUtils.getAgeMoreThan18()},  // one day more than 18
                {DateUtils.getAgeEqual18()},  // 18 ages
        };
    }

    @DataProvider(name = "Young Age")
    public static Object[][] yuongBirthDatesProvider() {
        return new Object[][]{
                {DateUtils.getAgeZero()},  // 0 (zero) age
                {DateUtils.getAgeLessThan18()}  // one day less than 18
        };
    }

    @DataProvider(name = "Invalid Dates")
    public static Object[][] invalidDates() {
        return new Object[][]{
                {"not-a-date"},
                {"32-01-2020"},
                {"1900-01-01"},
                {"3024-12-31"},
                {"15.20.1975"},
                {""},
                {null}
        };
    }
}
