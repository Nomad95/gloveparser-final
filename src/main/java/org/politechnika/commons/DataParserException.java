package org.politechnika.commons;

import org.politechnika.cache.ErrorCache;

public class DataParserException extends Exception {

    protected DataParserException(String message) {
        super(message);
        ErrorCache.addError(message, null);
    }

    protected DataParserException(String message, Throwable cause) {
        super(message, cause);
        ErrorCache.addError(message, cause);
    }
}
