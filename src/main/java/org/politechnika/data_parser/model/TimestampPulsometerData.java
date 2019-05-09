package org.politechnika.data_parser.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.politechnika.commons.Optimized;
import org.politechnika.data_parser.converter.TimestampToInstantConverter;
import org.politechnika.model.pulsometer.PulsometerValueDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Optimized(optimizationFactor = 120)
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class TimestampPulsometerData implements DataDto {

    @CsvBindByName(column = "HeartRate")
    private Integer value;

    @CsvCustomBindByName(column = "TimeStamp", converter = TimestampToInstantConverter.class)
    private Instant timestamp;

    public PulsometerDataDto toPulsometerDto() {
        LocalDateTime ltd = LocalDateTime.ofInstant(timestamp, ZoneOffset.UTC);
        return new PulsometerDataDto(ltd.toLocalDate(), ltd.toLocalTime(), value);
    }

    public PulsometerValueDto toValueDto() {
        return new PulsometerValueDto(
                timestamp, value);
    }
}
