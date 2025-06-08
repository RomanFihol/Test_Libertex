package functional;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DateUtils;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CheckAge {

    private static final Logger log = LoggerFactory.getLogger(CheckAge.class);

    @DataProvider(name = "Adulthood")
    public static Object[][] adultBirthDatesProvider() {
        return new Object[][]{
                {DateUtils.getDate20YearsAgo()},  // 20 years
                {DateUtils.getAgeMoreThan18()},  // one day more than 18
                {DateUtils.getAgeEqual18()},  // 18 ages
        };
    }

    @DataProvider(name = "Young Age")
    public static Object[][] youngBirthDatesProvider() {
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

    @Test(description = "Check Adult Age", dataProvider = "Adulthood")
    public void checkIfAdult(String birthDay) {
        assertTrue(validateAge(birthDay));
    }

    @Test(description = "Check Young Age", dataProvider = "Young Age")
    public void checkIfYoung(String birthDay) {
        assertFalse(validateAge(birthDay));
    }

    @Test(description = "Check Invalid Dates", dataProvider = "Invalid Dates")
    public void checkInvalidDates(String birthDay) {
        assertFalse(validateAge(birthDay));
    }
    @Step("Check that age is valid: {birthDay}")
    public boolean validateAge(String birthDay) {
        if (birthDay == null || birthDay.isBlank()) {
            log("Birth date is null or empty.");
            return false;
        }

        if (!DateUtils.isValidDateFormat(birthDay)) {
            log("Invalid date format: " + birthDay);
            return false;
        }

        if (!DateUtils.isAgeRealistic(birthDay)) {
            log("Age is out of valid range: " + birthDay);
            return false;
        }

        if (!DateUtils.isAdult(birthDay)) {
            log("Age is less than 18: " + birthDay);
            return false;
        }

        log("Age is valid: " + birthDay);
        return true;
    }

    @Step("Logging message: {message}")
    private void log(String message) {
        System.out.println(message);
    }
}