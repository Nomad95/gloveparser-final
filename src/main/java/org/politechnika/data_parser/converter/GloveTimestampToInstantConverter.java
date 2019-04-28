package org.politechnika.data_parser.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class GloveTimestampToInstantConverter extends AbstractBeanField<Instant> {

    @Override
    protected Instant convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (Objects.isNull(value)) return null;
        if (value.isEmpty()) return null;

        return LocalDateTime
                .parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd-kk-mm-ss-SSSS"))
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }
}
