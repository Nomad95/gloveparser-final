package org.politechnika.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

/**
 * Throws NullPointerException if cache is not initialized
 *
 * Remember to evict cache to save memory
 * NOT THREAD SAFE
 */
@Slf4j
public class LoadingStringCache {

    private static LoadingCache<EntryType, String> stringCache;

    public static void initCache() {
        if (isNull(stringCache)) {
            init();
        }
    }

    private static void init() {
        stringCache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .build(new CacheLoader<EntryType, String>() {
                @Override
                public String load(EntryType type) throws Exception {
                    return stringCache.get(type);
                }
            });
    }

    public static void put(EntryType type, String value) {
        stringCache.put(type, value);
    }

    public static String get(EntryType type) {
        String maybeValue = stringCache.getIfPresent(type);
        if (isNull(maybeValue))
            return "";
        return maybeValue;
    }

    public static void evict() {
        stringCache.invalidateAll();
    }

    public enum EntryType {
        GLOVE_STATS
    }
}
