package org.politechnika.file;

import org.politechnika.commons.DataParserException;

public class ParserWriteFileException extends DataParserException {
    protected ParserWriteFileException(String message) {
        super(message);
    }

    protected ParserWriteFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
