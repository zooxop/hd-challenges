package com.chmun.chart.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String convertToString(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(formatter);
    }

    public static LocalDate convertToLocalDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }

        return LocalDate.parse(dateString, formatter);
    }
}
