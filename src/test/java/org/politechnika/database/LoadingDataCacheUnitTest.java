package org.politechnika.database;

import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.politechnika.cache.EntryType;
import org.politechnika.cache.LoadingDataCache;
import org.politechnika.data_parser.model.DataDto;
import org.politechnika.data_parser.model.GloveDataDto;

import java.util.List;

public class LoadingDataCacheUnitTest {

    @BeforeClass
    public static void setUp() {
        LoadingDataCache.initCache();
    }

    @After
    public void after() {
        LoadingDataCache.evict();
    }

    @Test
    public void shouldPutGenericDataToCacheAndBePresent() {
        DataDto gloveDataDto = new GloveDataDto();

        LoadingDataCache.put(EntryType.KINECT_STATS, Lists.newArrayList(gloveDataDto));
        List<GloveDataDto> gloveDataDtos = LoadingDataCache.get(EntryType.KINECT_STATS);

        Assert.assertFalse(gloveDataDtos.isEmpty());
        Assert.assertEquals(gloveDataDto, gloveDataDtos.get(gloveDataDtos.size() - 1));
    }

    @Test
    public void shouldReturnEmptyListWhenGettingNonExistingData() {
        List<GloveDataDto> gloveDataDtos = LoadingDataCache.get(EntryType.KINECT_STATS);

        Assert.assertTrue(gloveDataDtos.isEmpty());
    }
}
