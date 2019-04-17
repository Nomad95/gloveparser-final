package org.politechnika.data_parser;

import org.politechnika.commons.DataParserException;

public class CsvParsingException extends DataParserException {

    CsvParsingException(String message) {
        super(message);
    }

    CsvParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
