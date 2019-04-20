package org.politechnika.data_parser.model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.politechnika.commons.Optimized;
import org.politechnika.data_parser.converter.PulsometerDateConverter;
import org.politechnika.data_parser.converter.PulsometerTimeConverter;
import org.politechnika.model.pulsometer.PulsometerValueDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Getter
@Optimized(optimizationFactor = 120)
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class PulsometerDataDto implements DataDto {

    @CsvCustomBindByPosition(position = 0, converter = PulsometerDateConverter.class)
    private LocalDate date;

    @CsvCustomBindByPosition(position = 1, converter = PulsometerTimeConverter.class)
    private LocalTime time;

    @CsvBindByPosition(position = 3)
    private Integer value;

    public PulsometerValueDto toValueDto() {
        return new PulsometerValueDto(
                LocalDateTime.of(date, time).toInstant(ZoneOffset.UTC),
                value);
    }
}
