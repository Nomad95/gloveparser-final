package org.politechnika;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

public final class StaticTestResources {

    public static String GLOVE_TEST_DATA;
    public static String PULSOMETER_TEST_DATA;

    static {
        try {
            GLOVE_TEST_DATA = IOUtils.toString(StaticTestResources.class.getClassLoader().getResourceAsStream("GLOVE.csv"));
            PULSOMETER_TEST_DATA = IOUtils.toString(StaticTestResources.class.getClassLoader().getResourceAsStream("PULSOMETER.csv"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
