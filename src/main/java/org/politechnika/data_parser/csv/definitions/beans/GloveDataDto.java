package org.politechnika.data_parser.csv.definitions.beans;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.politechnika.data_parser.csv.definitions.DataDto;
import org.politechnika.data_parser.csv.definitions.beans.field_definition.DoubleWithCommaConverter;
import org.politechnika.data_parser.csv.definitions.beans.field_definition.GloveTimestampToInstantConverter;

import java.time.Instant;

@Getter
@EqualsAndHashCode
public class GloveDataDto implements DataDto {

    //Hand|SensorNumber|Scale|Raw|Low|Up|TimeStamp
    //left|0|0,4166667|2847|2832|2868|2018-06-10-11-48-34-7705
    //zmienna długość okresu//TODO: mzoe dodac checkboxy we froncie zeby wybierac ktorte wykresy ma dawac? xD

    @CsvBindByName(column = "Hand")
    private String hand;

    @CsvBindByName(column = "SensorNumber")
    private short sensorNumber;

    @CsvCustomBindByName(column = "Scale", converter = DoubleWithCommaConverter.class)
    private double scale; //scale = (raw-low)/(up-low)

    @CsvBindByName(column = "Raw")
    private int raw;

    @CsvBindByName(column = "Low")
    private int low;

    @CsvBindByName(column = "Up")
    private int up;

    @CsvCustomBindByName(column = "TimeStamp", converter = GloveTimestampToInstantConverter.class)
    private Instant timestamp;
}
