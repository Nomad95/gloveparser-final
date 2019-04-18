package org.politechnika.commons;

public class IllegalParserStateException extends IllegalStateException {

    public IllegalParserStateException(String s) {
        super(s);
    }

    public IllegalParserStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
