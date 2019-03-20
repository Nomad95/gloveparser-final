package org.politechnika.data_parser.csv.definitions.beans;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.politechnika.data_parser.csv.definitions.DataDto;
import org.politechnika.data_parser.csv.definitions.beans.field_definition.PulsometerDateConverter;
import org.politechnika.data_parser.csv.definitions.beans.field_definition.PulsometerTimeConverter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
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
}
