package org.politechnika.data_parser.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Value;

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
