package org.politechnika.data_parser.csv.definitions.beans.field_definition;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.util.Objects;

public class DoubleWithCommaConverter extends AbstractBeanField<Double> {

    @Override
    protected Double convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (Objects.isNull(value)) return 0d;
        if (value.isEmpty()) return 0d;

        value = value.replace(",", ".");
        return Double.parseDouble(value);
    }
}
