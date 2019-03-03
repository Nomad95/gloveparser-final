package org.politechnika.model;

import java.security.InvalidParameterException;

public enum Finger {

    THUMB {
        @Override
        String getFingerName() {
            return "Kciuk";
        }
    },
    INDEX {
        @Override
        String getFingerName() {
            return "Wskazujący";
        }
    },
    MIDDLE {
        @Override
        String getFingerName() {
            return "Środkowy";
        }
    },
    RING {
        @Override
        String getFingerName() {
            return "Serdeczny";
        }
    },
    LITTLE {
        @Override
        String getFingerName() {
            return "Mały";
        }
    };

    abstract String getFingerName();

    public static Finger getFingerBySensorNumber(int sensorNumber) {
        if (sensorNumber == 1 || sensorNumber == 0)
            return THUMB;
        if (sensorNumber == 3 || sensorNumber == 4)
            return INDEX;
        if (sensorNumber == 6 || sensorNumber == 7)
            return MIDDLE;
        if (sensorNumber == 9 || sensorNumber == 10)
            return RING;
        if (sensorNumber == 12 || sensorNumber == 13)
            return LITTLE;

        throw new InvalidParameterException(sensorNumber + " is not a valid finger sensor");
    }
}
