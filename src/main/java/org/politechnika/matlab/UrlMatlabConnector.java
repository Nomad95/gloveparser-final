package org.politechnika.matlab;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import static java.util.Objects.requireNonNull;


@Slf4j
@RequiredArgsConstructor
public class UrlMatlabConnector implements MatlabConnector {

    private final String filePath;

    @Override
    public MatlabEngine startSession() throws EngineException {
        try {
            log.info("Starting MatLab instance...");
            URL url = new File(requireNonNull(filePath)).toURI().toURL();
            URLClassLoader child = new URLClassLoader(new URL[]{url}, this.getClass().getClassLoader());
            Class<?> classToLoad = Class.forName("com.mathworks.engine.MatlabEngine", true, child);
            Method startMatlabMethod = classToLoad.getMethod("startMatlab");
            MatlabEngine session = (MatlabEngine) startMatlabMethod.invoke(null);
            log.info("done");
            return session;
        } catch (MalformedURLException e) {
            log.error("Could not load engine.jar file", e);
        } catch (ClassNotFoundException e) {
            log.error("Could not find com.mathworks.engine.MatlabEngine in engine.jar file", e);
        } catch (NoSuchMethodException e) {
            log.error("There is no method like 'startMatlab()' in engine.jar", e);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("Could not access startMatlab() method reflectively");
        } catch (Exception e) {
            if (e instanceof InterruptedException)
                log.error("Connecting to MatLab was interrupted by another thread", e);
            else throw e;
        }

        throw new EngineException("Unable to start MatLab");
    }
}
