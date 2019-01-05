package org.politechnika.matlab.builders.command;

import com.mathworks.engine.MatlabEngine;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
public class SaveImage implements EvalCommand {

    private String path;
    private String filename;

    public SaveImage(@NonNull String path, String filename) {
        this.path = path;
        this.filename = filename;
    }

    @Override
    public void evaluate(MatlabEngine matlabEngine) throws ExecutionException, InterruptedException {
        log.debug("Matlab: evaluated: saveas()");
        matlabEngine.eval("saveas(gcf,'" + path + "\\" + filename + ".png" + "');", null, null);
        log.debug("Matlab: evaluated: close()");
        matlabEngine.eval("close(gcf);", null, null);
    }

    public static SaveImage saveImageAs(@NonNull String path, String filename) {
        return new SaveImage(path, filename);
    }
}
