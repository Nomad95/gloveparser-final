package org.politechnika.cache;

import java.util.ArrayList;
import java.util.List;

public class ReportStepCache {

    private static final List<String> STEPS = new ArrayList<>(64);
    private static String currentStep = "";

    public static void addStep(String stepDescription) {
        STEPS.add(stepDescription);
        currentStep = stepDescription;
    }
}
