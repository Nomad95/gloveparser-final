package org.politechnika.report.pulsometer_functions;

import org.politechnika.cache.EntryType;
import org.politechnika.cache.LoadingDataCache;
import org.politechnika.data_parser.model.PulsometerDataDto;
import org.politechnika.model.pulsometer.PulsometerValueDto;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class StorePulsometerValues implements UnaryOperator<List<PulsometerDataDto>> {

    @Override
    public List<PulsometerDataDto> apply(List<PulsometerDataDto> pulsometerDataDtos) {
        LinkedList<PulsometerValueDto> values = pulsometerDataDtos.stream()
                .map(PulsometerDataDto::toValueDto)
                .collect(Collectors.toCollection(LinkedList::new));

        LoadingDataCache.put(EntryType.PULSOMETER_VALUES, values);
        return pulsometerDataDtos;
    }
}
