package org.politechnika.data_parser.csv.definitions.beans.field_definition;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PulsometerDateConverter extends AbstractBeanField<LocalDate> {

    @Override
    protected LocalDate convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (Objects.isNull(value)) return null;
        if (value.isEmpty()) return null;


        return LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
