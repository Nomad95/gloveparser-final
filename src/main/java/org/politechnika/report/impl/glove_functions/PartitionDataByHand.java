package org.politechnika.report.impl.glove_functions;

import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

public class PartitionDataByHand implements Function<List<GloveDataDto>, Map<String, List<GloveDataDto>>> {

    @Override
    public Map<String, List<GloveDataDto>> apply(List<GloveDataDto> gloveDataDtos) {
        return gloveDataDtos.stream().collect(groupingBy(GloveDataDto::getHand));
    }
}
