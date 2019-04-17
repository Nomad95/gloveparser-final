package org.politechnika.data_parser.csv.impl;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.politechnika.commons.Separators;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CsvBeanParser {

    //FIXME
    public <T> String parseToCsv(List<T> beans, String path){
        Writer writer = null;
        try {
            writer = new FileWriter(path);
            StatefulBeanToCsv<T> parser = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(Separators.SEMICOLON).build();
            parser.write(beans);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }
}
