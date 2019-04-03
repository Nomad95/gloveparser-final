package org.politechnika.commons;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class NumberCommons {

    public static int tryGetIntValueFromString(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            log.error("Something went wrong ;p, recovering with default value of 1000 {}", e);
        }

        return 1000;
    }
}
