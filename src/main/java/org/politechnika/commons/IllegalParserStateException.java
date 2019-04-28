package org.politechnika.commons;

import org.politechnika.cache.ErrorCache;

public class IllegalParserStateException extends IllegalStateException {

    public IllegalParserStateException(String s) {
        super(s);
        ErrorCache.addError(s, null);
    }

    public IllegalParserStateException(String message, Throwable cause) {
        super(message, cause);
        ErrorCache.addError(message, cause);
    }
}
