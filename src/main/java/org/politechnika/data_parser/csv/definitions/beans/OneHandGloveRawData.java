package org.politechnika.data_parser.csv.definitions.beans;

import com.opencsv.bean.CsvBindByName;
import lombok.Value;
import org.politechnika.data_parser.csv.definitions.DataDto;

@Value
public class OneHandGloveRawData implements DataDto {

    @CsvBindByName(column = "kciuk")
    private double thumb;

    @CsvBindByName(column = "wskazujacy")
    private double index;

    @CsvBindByName(column = "srodkowy")
    private double middle;

    @CsvBindByName(column = "serdeczny")
    private double ring;

    @CsvBindByName(column = "maly")
    private double little;
}
