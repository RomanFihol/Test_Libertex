package utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //  Getting date 20 years ago
    public static String getDate20YearsAgo() {
        return LocalDate.now()
                .minusYears(20)
                .format((FORMATTER));
    }

    //  Getting some future date
    public static String getFutureDate() {
        return LocalDate.now()
                .plusYears(20)
                .format(FORMATTER);
    }

    //  Getting 18 years old
    public static String getAgeEqual18() {
        return LocalDate.now()
                .minusYears(18)
                .format(FORMATTER);
    }

    //  Getting 0 (zero) age
    public static String getAgeZero() {
        return LocalDate.now()
                .format(FORMATTER);
    }

    //  Getting 1 day younger than 18 years old
    public static String getAgeLessThan18() {
        return LocalDate.now()
                .minusYears(18)
                .plusDays(1)
                .format(FORMATTER);
    }

    //  Getting 1 day older than 18 years old
    public static String getAgeMoreThan18() {
        return LocalDate.now()
                .minusYears(18)
                .minusDays(1)
                .format(FORMATTER);
    }

    // The method for calculating age
    public int calculateAge(String birthDateStr) {
        LocalDate birthDay = LocalDate.parse(birthDateStr, FORMATTER);
        return Period.between(birthDay, LocalDate.now()).getYears();
    }
}