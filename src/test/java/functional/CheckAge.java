package functional;

import base.BirthDayDataProvider;
import exceptions.UnderAgeException;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import utils.DateUtils;
import java.time.format.DateTimeParseException;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CheckAge {

    @Step
    public boolean checkAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age can not be negative");
        } else if (age < 18) {
            throw new UnderAgeException("Age is less than 18");
        } else if (age > 130) {
            throw new IllegalArgumentException("Age is impossible: " + age);
        }
        return true;
    }

    @Step("Validate age: {birthDay}")
    public boolean validateAge(String birthDay) {
        try {
            if (birthDay == null || birthDay.isBlank()) {
                System.out.println("Birth date is null or empty.");
                return false;
            }
            DateUtils dateUtils = new DateUtils();
            int age = dateUtils.calculateAge(birthDay);

            if (age > 120) {
                System.out.println("Unrealistically old age: " + age);
                return false;
            }
            return checkAge(age);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + birthDay);
            return false;
        } catch (UnderAgeException e) {
            System.out.println("Underage: " + birthDay);
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return false;
        }
    }

    @Test(description = "Check Adult Age", dataProvider = "Adulthood", dataProviderClass = BirthDayDataProvider.class)
    public void checkIfAdult(String birthDay) {
        assertTrue(validateAge(birthDay), "Expected age to be valid: " + birthDay);
    }

    @Test(description = "Check Young Age", dataProvider = "Young Age", dataProviderClass = BirthDayDataProvider.class)
    public void checkIfYoung(String birthDay) {
        assertFalse(validateAge(birthDay), "Expected to be under 18: " + birthDay);
    }

    @Test(description = "Check Invalid Dates", dataProvider = "Invalid Dates", dataProviderClass = BirthDayDataProvider.class)
    public void checkInvalidDates(String birthDay) {
        assertFalse(validateAge(birthDay), "Expected invalid date to fail: " + birthDay);
    }
}