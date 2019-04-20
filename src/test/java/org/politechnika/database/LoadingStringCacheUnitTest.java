package org.politechnika.database;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.politechnika.cache.LoadingStringCache;

import static org.politechnika.cache.EntryType.LEFT_HAND_STATS;


public class LoadingStringCacheUnitTest {

    @BeforeClass
    public static void setUp() {
        LoadingStringCache.initCache();
    }

    @After
    public void after() {
        LoadingStringCache.evict();
    }

    @Test
    public void shouldPutStringToCache() {
        String sample = "asdiabsid asnd iuashd ias" +
                "asidn asjdna sdnaos do a" +
                "asdn askdn asdn aos d\n" +
                "asdaosdasodmaskdmasko ";

        LoadingStringCache.put(LEFT_HAND_STATS, sample);
        String gloveStats = LoadingStringCache.get(LEFT_HAND_STATS);

        Assert.assertEquals(sample, gloveStats);
    }

}
