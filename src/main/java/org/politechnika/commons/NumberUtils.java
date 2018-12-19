package org.politechnika.commons;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class NumberUtils {

    public static int tryGetIntValueFromString(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            log.error("Something went wrong, recovering with value of 0 ex: {}", e);
        }

        return 0;
    }
}
