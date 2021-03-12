package ru.mashka.utils;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;

import static java.time.temporal.ChronoField.*;

public final class DateUtils {

    private DateUtils() {
    }

    private static final DateTimeFormatter MY_DATE_TIME_FORMAT;

    static {
        MY_DATE_TIME_FORMAT = new DateTimeFormatterBuilder()
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral('.')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('.')
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .appendLiteral(' ')
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .optionalStart()
                .toFormatter()
                .withZone(ZoneOffset.UTC);

    }

    public static Instant parse(CharSequence text, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.parse(text, Instant::from);
    }

    public static Instant parse(CharSequence text) {
        return parse(text, MY_DATE_TIME_FORMAT);
    }
}
