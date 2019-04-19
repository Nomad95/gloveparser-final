package org.politechnika.database;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.politechnika.cache.LoadingStringCache;

import static org.politechnika.cache.LoadingStringCache.EntryType.GLOVE_STATS;

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

        LoadingStringCache.put(GLOVE_STATS, sample);
        String gloveStats = LoadingStringCache.get(GLOVE_STATS);

        Assert.assertEquals(sample, gloveStats);
    }

}
