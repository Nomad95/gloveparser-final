package org.politechnika.database;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.data_parser.model.DataDto;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;

/**
 * Throws NullPointerException if cache is not initialized
 *
 * Remember to evict cache to save memory
 * NOT THREAD SAFE
 */
@Slf4j
public class LoadingDataCache {

    private static LoadingCache<Class<?>, List<? extends DataDto>> dataCache;

    public static void initCache() {
        if (isNull(dataCache)) {
            init();
        }
    }

    private static void init() {
        dataCache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(new CacheLoader<Class<?>, List<? extends DataDto>>() {
                @Override
                public List<? extends DataDto> load(Class<?> aClass) throws Exception {
                    return dataCache.get(aClass);
                }
            });
    }

    public static <T extends DataDto> void put(Class<T> type, List<? extends DataDto> list) {
        dataCache.put(type, list);
    }

    public static <T extends DataDto> List<T> get(Class<T> type) {
        List<? extends DataDto> maybeDataList = dataCache.getIfPresent(type);
        if (isNull(maybeDataList))
            return Collections.emptyList();
        @SuppressWarnings("unchecked") //type checking at 'put' method so casting is safe
        List<T> list = (List<T>) maybeDataList;
        return list;
    }

    public static void evict() {
        dataCache.invalidateAll();
    }
}
