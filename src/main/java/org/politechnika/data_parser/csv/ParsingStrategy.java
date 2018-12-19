package org.politechnika.data_parser.csv;

public interface ParsingStrategy<BEAN> {

    Class<BEAN> getBeanClass();

    char getSeparator();

}
