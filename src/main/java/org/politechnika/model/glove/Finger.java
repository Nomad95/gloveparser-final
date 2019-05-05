package org.politechnika.model.glove;

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
        if (sensorNumber == 1 || sensorNumber == 0 || sensorNumber == 2)
            return THUMB;
        if (sensorNumber == 3 || sensorNumber == 4 || sensorNumber == 5)
            return INDEX;
        if (sensorNumber == 6 || sensorNumber == 7 || sensorNumber == 8)
            return MIDDLE;
        if (sensorNumber == 9 || sensorNumber == 10 || sensorNumber == 11)
            return RING;
        if (sensorNumber == 12 || sensorNumber == 13)
            return LITTLE;

        //todo: sie okazało ze gdzies była wartość z sensora 2 - co robimy w takim wypadku?
        throw new InvalidParameterException(sensorNumber + " is not a valid finger sensor");
    }
}
