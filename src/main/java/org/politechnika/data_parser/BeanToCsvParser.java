package org.politechnika.data_parser;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.Separators;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Slf4j
public class BeanToCsvParser {

    public <T> void parseToCsv(List<T> beans, String path) throws CsvParsingException {
        try (Writer writer = new FileWriter(path)) {
            StatefulBeanToCsv<T> parser = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(Separators.SEMICOLON).build();
            parser.write(beans);
        } catch (CsvDataTypeMismatchException e) {
            log.error("Data type mismatch: {}", e.getMessage());
            throw new CsvParsingException("Data type mismatch", e);
        } catch (CsvRequiredFieldEmptyException e) {
            log.error("Some field was required; {}", e.getMessage());
            throw new CsvParsingException("Required field was empty", e);
        } catch (IOException e) {
            log.error("Error while writing data: {}", e.getMessage());
            throw new CsvParsingException("Data type mismatch", e);
        }
    }
}
