package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
    public static boolean isBetweenDate(LocalDate ld, String startDate, String endDate) {
        if (startDate.isEmpty() || endDate.isEmpty())
            return true;

        LocalDate sd = LocalDate.parse(startDate, DATE_FORMATTER);
        LocalDate ed = LocalDate.parse(endDate, DATE_FORMATTER);
        return ld.compareTo(sd) >= 0 && ld.compareTo(ed) <= 0;
    }
    public static boolean isBetweenTime(LocalTime lt, String startTime, String endTime) {
        if (startTime.isEmpty() || endTime.isEmpty())
            return true;

        LocalTime st = LocalTime.parse(startTime, TIME_FORMATTER);
        LocalTime et = LocalTime.parse(endTime, TIME_FORMATTER);
        return lt.compareTo(st) >= 0 && lt.compareTo(et) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}


