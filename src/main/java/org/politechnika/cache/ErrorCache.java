package org.politechnika.cache;

import lombok.Value;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to maintain a log of all data parser errors that we maybe want to preserve
 */
public class ErrorCache {

    private static final List<ErrorEntry> errors = new ArrayList<>(64);

    public static void addError(String message, Throwable cause) {
        errors.add(new ErrorEntry(message, cause, ZonedDateTime.now(ZoneId.of("Europe/Warsaw"))));
    }

    @Value
    public static class ErrorEntry {
        private String message;
        private Throwable cause;
        private ZonedDateTime dateTime;
    }
}
