package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Dates {
    private static DateTimeFormatter dateTimeFormatter;

    private Dates() {

    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        if (dateTimeFormatter == null)
            dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

        return localDateTime.format(dateTimeFormatter);
    }
}
