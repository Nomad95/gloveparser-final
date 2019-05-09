package org.politechnika.data_parser.strategy;

public interface ParsingStrategy<BEAN> {

    Class<BEAN> getBeanClass();

    char getReadSeparator();

    char getWriteSeparator();

    boolean supportsFileExtension(String ext);
}
