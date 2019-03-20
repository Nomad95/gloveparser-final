package org.politechnika.data_parser.csv.definitions.beans.field_definition;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PulsometerTimeConverter extends AbstractBeanField<LocalTime> {

    @Override
    protected LocalTime convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (Objects.isNull(value)) return null;
        if (value.isEmpty()) return null;

        return LocalTime.parse(value, DateTimeFormatter.ofPattern("kk:mm:ss.SSS"));
    }
}
