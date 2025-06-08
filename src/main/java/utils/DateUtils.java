package utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //  Getting date 20 years ago
    public static String getDate20YearsAgo() {
        return LocalDate.now()
                .minusYears(20)
                .format((FORMATTER));
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
    public static int calculateAge(String birthDateStr) {
        LocalDate birthDay = LocalDate.parse(birthDateStr, FORMATTER);
        return Period.between(birthDay, LocalDate.now()).getYears();
    }

    public static boolean isValidDateFormat(String dateStr) {
        try {
            LocalDate.parse(dateStr, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isAgeRealistic(String dateStr) {
        if (!isValidDateFormat(dateStr)) return false;

        int age = calculateAge(dateStr);
        return age >= 0 && age <= 120;
    }

    public static boolean isAdult(String dateStr) {
        if (!isValidDateFormat(dateStr)) return false;

        return calculateAge(dateStr) >= 18;
    }
}