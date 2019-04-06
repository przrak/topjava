package ru.javawebinar.topjava.web.converter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {

    private String pattern;

    public LocalTimeFormatter(String pattern) {
        this.pattern = pattern;
    }

    public String print(LocalTime time, Locale locale) {
        return (time == null) ? "" : time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public LocalTime parse(String formatted, Locale locale) throws ParseException {
        return (formatted.length() == 0) ? null : LocalTime.parse(formatted);
    }
}
