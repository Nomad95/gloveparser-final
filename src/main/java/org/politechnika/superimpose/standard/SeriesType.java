package org.politechnika.superimpose.standard;

import org.politechnika.data_parser.model.TimeSequential;

import java.util.List;

public enum SeriesType {
    LEFT_HAND{
        @Override
        List<? extends TimeSequential> getSeries(StandardSuperimposedChart stdSuper) {
            return stdSuper.leftGloveValues;
        }
    },
    RIGHT_HAND {
        @Override
        List<? extends TimeSequential> getSeries(StandardSuperimposedChart stdSuper) {
            return stdSuper.rightGloveValues;
        }
    },
    PULS {
        @Override
        List<? extends TimeSequential> getSeries(StandardSuperimposedChart stdSuper) {
            return stdSuper.pulsometerValues;
        }
    },
    KINECT {
        @Override
        List<? extends TimeSequential> getSeries(StandardSuperimposedChart stdSuper) {
            return stdSuper.kinectValues;
        }
    };

    abstract List<? extends TimeSequential> getSeries(StandardSuperimposedChart stdSuper);
}
